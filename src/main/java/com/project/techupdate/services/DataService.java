package com.project.techupdate.services;

import com.project.techupdate.entity.Data;
import com.project.techupdate.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Data getDataById(Long id){
        Optional<Data> dataOptional = dataRepository.findById(id);
        if(!dataOptional.isPresent()){
            throw new Error("Data not found");
        }
        return dataOptional.get();
    }
}
