package com.project.techupdate.controller;

import com.project.techupdate.entity.Data;
import com.project.techupdate.entity.Role;
import com.project.techupdate.entity.User;
import com.project.techupdate.repository.DataRepository;
import com.project.techupdate.repository.UserRepository;
import com.project.techupdate.request.UserRequest;
import com.project.techupdate.services.RoleService;
import com.project.techupdate.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest){
        List<Role> roles = roleService.getRoleByIds(userRequest.getRoleIds());
        if(roles == null || userRequest.getPassword().length() <= 7 || !userRequest.getPassword().equals(userRequest.getConfirmPassword())){
            return ResponseEntity.badRequest().build();
        }
        Set<Role> roleSet = new HashSet<>();
        for(Role role: roles)
            roleSet.add(role);

        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setRole(roleSet);
        user = userService.createUser(user);
        if(user == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }
}
