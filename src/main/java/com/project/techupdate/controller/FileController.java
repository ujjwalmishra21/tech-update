package com.project.techupdate.controller;

import com.project.techupdate.response.FileResponse;
import com.project.techupdate.entity.File;
import com.project.techupdate.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileService fileService;

    @PostMapping("/upload-file")
    public ResponseEntity<FileResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        if(multipartFile == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if(!fileService.fileNameAvailable(multipartFile.getOriginalFilename())){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        File file = fileService.saveFile(multipartFile);
        FileResponse responseDTO = new FileResponse();
        responseDTO.setFileName(file.getFileName());
        responseDTO.setId(file.getId());
        responseDTO.setMessage("File saved successfully");
        return ResponseEntity.ok(responseDTO);
    }

}
