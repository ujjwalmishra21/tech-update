package com.project.techupdate.repository;


import com.project.techupdate.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findByIdIn(List<Long> idList);
    File findByFileName(String name);
}
