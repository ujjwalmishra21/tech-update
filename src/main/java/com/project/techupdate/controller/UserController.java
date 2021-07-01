package com.project.techupdate.controller;

import com.project.techupdate.model.entity.Role;
import com.project.techupdate.model.entity.User;
import com.project.techupdate.model.request.UserRequest;
import com.project.techupdate.services.RoleService;
import com.project.techupdate.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
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

        if(userRequest.getRoleIds() == null)
            userRequest.setRoleIds(new ArrayList<Long>(){{add(2l);}});

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
