package com.project.techupdate.services;

import com.project.techupdate.entity.Role;
import com.project.techupdate.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> getRoleByIds(List<Long> ids){
        List<Role> roles = roleRepository.findByIdIn(ids);

        return roles;
    }

}
