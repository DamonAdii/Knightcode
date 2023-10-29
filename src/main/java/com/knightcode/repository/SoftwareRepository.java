package com.knightcode.repository;

import com.knightcode.model.Follow;
import com.knightcode.model.Skill;
import com.knightcode.model.Software;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SoftwareRepository extends JpaRepository<Software,Long> {

    public List<Software> findByNameContaining(String name);

    public Software findByName(String name);

}
