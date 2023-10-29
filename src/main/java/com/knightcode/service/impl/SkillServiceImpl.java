package com.knightcode.service.impl;

import com.knightcode.model.Skill;
import com.knightcode.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl {

    private final SkillRepository skillRepository;


    public List<Skill> searchSkills(String skillName) {
        return skillRepository.findByNameContaining(skillName);
    }


}
