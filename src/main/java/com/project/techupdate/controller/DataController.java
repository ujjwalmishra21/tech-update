package com.project.techupdate.controller;

import com.project.techupdate.dto.DataDTO;
import com.project.techupdate.entity.Data;
import com.project.techupdate.entity.File;
import com.project.techupdate.services.DataService;
import com.project.techupdate.services.FileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/data")
public class DataController {
    @Autowired
    DataService dataService;

    @Autowired
    FileService fileService;

    @PostMapping("/addData")
    public ResponseEntity<Data> addData(@RequestBody DataDTO dataDTO){
        Data data = convertDataDTOToData(dataDTO);
        List<File> files = fileService.getFile(dataDTO.getIdList());
        data.setFiles(files);
        Data dataResponse = dataService.addData(data);
        if(dataResponse == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(dataResponse);
    }

    @GetMapping
    public ResponseEntity<List<Data>> getData(){
        return ResponseEntity.ok(dataService.findAll());
    }

    public static Data convertDataDTOToData(DataDTO dataDTO){
        Data data = new Data();
        BeanUtils.copyProperties(dataDTO,data);
        return data;
    }
}
