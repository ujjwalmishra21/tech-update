package com.project.techupdate.services;

import com.project.techupdate.entity.Data;
import com.project.techupdate.entity.User;
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

    public Data likeUnlikeData(Long id, User user) {
        Optional<Data> dataOptional = dataRepository.findById(id);
        if(!dataOptional.isPresent()){
            throw new Error("Invalid post id");
        }
        Data data = dataOptional.get();
        List<User> likes = data.getLikes();
        boolean unlike = false;

        for(int i=0; i<likes.size(); i++){
            if(likes.get(i).getId() == user.getId()){
                likes.remove(i);
                unlike = true;
                break;
            }
        }
        if(!unlike){
            likes.add(user);
        }

        data.setLikes(likes);
        dataRepository.save(data);
        return data;
    }
}
