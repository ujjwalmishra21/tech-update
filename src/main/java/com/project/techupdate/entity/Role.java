package com.project.techupdate.entity;



import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

//    @ManyToMany(mappedBy = "")
//    private Set<User> user;
//
//    public Set<User> getUser() {
//        return user;
//    }
//
//    public void setUser(Set<User> user) {
//        this.user = user;
//    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
