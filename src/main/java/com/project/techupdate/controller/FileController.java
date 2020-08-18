package com.project.techupdate.controller;

import com.project.techupdate.dto.FileResponseDTO;
import com.project.techupdate.entity.File;
import com.project.techupdate.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileService fileService;

    @PostMapping("/upload-file")
    public ResponseEntity<FileResponseDTO> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        if(multipartFile == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if(!fileService.fileNameAvailable(multipartFile.getOriginalFilename())){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        File file = fileService.saveFile(multipartFile);
        FileResponseDTO responseDTO = new FileResponseDTO();
        responseDTO.setFileName(file.getFileName());
        responseDTO.setId(file.getId());
        responseDTO.setMessage("File saved successfully");
        return ResponseEntity.ok(responseDTO);
    }

}
