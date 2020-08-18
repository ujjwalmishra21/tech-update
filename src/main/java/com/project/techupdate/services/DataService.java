package com.project.techupdate.services;

import com.project.techupdate.entity.Data;
import com.project.techupdate.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {
    @Autowired
    DataRepository dataRepository;

    public Data addData(Data data){
        return dataRepository.save(data);
    }

    public List<Data> findAll(){
        return dataRepository.findAll();
    }
}
