package com.knightcode.repository;

import com.knightcode.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill,Long> {

    public List<Skill> findByNameContaining(String name);

    public Skill findByName(String name);

}
