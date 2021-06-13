package com.project.techupdate.controller;

import com.project.techupdate.model.dto.DataDTO;
import com.project.techupdate.model.dto.DataPartialDTO;
import com.project.techupdate.model.dto.PostLikeDTO;
import com.project.techupdate.model.entity.Data;
import com.project.techupdate.model.entity.File;
import com.project.techupdate.model.entity.User;
import com.project.techupdate.services.DataService;
import com.project.techupdate.services.FileService;
import com.project.techupdate.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/data")
public class DataController {
    @Autowired
    private DataService dataService;

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;


    @PostMapping("/add-data")
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

    @GetMapping("/{id}")
    public ResponseEntity<DataDTO> getDataById(@PathVariable Long id){
        Data data = dataService.getDataById(id);
        DataDTO dataDTO = convertDataToDataDTO(data);
        return ResponseEntity.ok(dataDTO);
    }

    @GetMapping
    public ResponseEntity<List<DataPartialDTO>> getData(){
        List<Data> dataList = dataService.findAll();
        List<DataPartialDTO> dataDTOS = new ArrayList<>();
        for(Data data : dataList){
            DataPartialDTO dataDTO = convertDataToDataPartialDTO(data);
            dataDTOS.add(dataDTO);
        }
        return ResponseEntity.ok(dataDTOS);
    }

    @PostMapping("/like")
    public ResponseEntity<DataDTO> likeData(@RequestBody PostLikeDTO post, Authentication auth){
        String username = auth.getPrincipal().toString();

        User user = userService.getUserByUsername(username);
        if(user == null){
//            return ResponseEntity.badRequest(null);
        }
        Data data = dataService.likeUnlikeData(post.getId(),user);
        DataDTO dataDTO = convertDataToDataDTO(data);
        return ResponseEntity.ok(dataDTO);
    }

    public static Data convertDataDTOToData(DataDTO dataDTO){
        Data data = new Data();
        BeanUtils.copyProperties(dataDTO,data);
        return data;
    }

    public static DataDTO convertDataToDataDTO(Data data){
        DataDTO dataDTO = new DataDTO();
        BeanUtils.copyProperties(data,dataDTO);
        return dataDTO;
    }

    public static DataPartialDTO convertDataToDataPartialDTO(Data data){
        DataPartialDTO dataDTO = new DataPartialDTO();
        BeanUtils.copyProperties(data,dataDTO);
        return dataDTO;
    }

}
