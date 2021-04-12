package com.project.techupdate.services;

import com.project.techupdate.entity.User;
import com.project.techupdate.repository.UserRepository;
import com.project.techupdate.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public User createUser(User user){

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return user;
    }

    public User getUserById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
           throw new Error("User not found");
        }
        return optionalUser.get();
    }

    public User getUserByUsername(String username){
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return user;
    }
}
