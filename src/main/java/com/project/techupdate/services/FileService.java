package com.project.techupdate.services;

import com.project.techupdate.entity.File;
import com.project.techupdate.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FileService {
    @Autowired
    FileRepository fileRepository;

    public File saveFile(MultipartFile multipartFile) throws IOException {

        String name = multipartFile.getOriginalFilename();
        String size = String.valueOf(multipartFile.getSize());
        String type = multipartFile.getContentType();
        InputStream fis = multipartFile.getInputStream();
        byte[] data = fis.readAllBytes();


        return fileRepository.save(new File(name,type,size,data));
    }

    public List<File> getFile(List<Long> idList){
        return fileRepository.findByIdIn(idList);
    }

    public boolean fileNameAvailable(String name){
        return fileRepository.findByFileName(name) == null;
    }
}
