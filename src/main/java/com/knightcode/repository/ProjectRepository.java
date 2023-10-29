package com.knightcode.repository;

import com.knightcode.model.Project;
import com.knightcode.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    public Project findByUser(User user);

}
