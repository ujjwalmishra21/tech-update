package com.project.techupdate.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserRequest {

    @JsonProperty
    private String username;

    @JsonProperty
    private String password;

    @JsonProperty
    private String confirmPassword;

    @JsonProperty
    private List<Long> roleIds;

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
