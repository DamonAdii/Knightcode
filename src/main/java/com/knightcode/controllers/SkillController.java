package com.knightcode.controllers;

import com.knightcode.model.*;
import com.knightcode.repository.*;
import com.knightcode.service.impl.SkillServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SkillController {


    private final SkillRepository repository;

    private final UserRepository userRepository;

    private final AboutRepository aboutRepository;

    private final EducationRepository educationRepository;

    private final ExperienceRepository experienceRepository;

    private final ProjectRepository projectRepository;

    private final SkillServiceImpl skillService;


    //search handler
    /*@GetMapping("/search-skills/{skillName}")
    public ResponseEntity<?> searchSkill(@PathVariable("skillName") String skillName, Principal principal){

        System.out.println("Skill Search query is :"+skillName);

        String username = principal.getName();

        User user = this.userRepository.findByEmail(username);

        List<Skill> skills = this.repository.findByNameContaining(skillName);

        return ResponseEntity.ok(skills);

    }*/


    @GetMapping("/search-skills")
    public List<Skill> searchSkills(@RequestParam("skillName") String skillName) {
        return skillService.searchSkills(skillName);
    }





    @PostMapping("/update-skills")
    public ResponseEntity<List<Skill>> updateSkills(@RequestBody List<String> selectedSkills, Principal principal) {
        log.info("Received selectedSkills: " + selectedSkills);

        String username = principal.getName();
        User user = this.userRepository.findByEmail(username);

        List<Skill> skillsToAdd = new ArrayList<>();

        for (String skillName : selectedSkills) {
            Skill skill = this.repository.findByName(skillName);
            if (skill != null && !user.getSkills().contains(skill)) {
                skillsToAdd.add(skill);
            }
        }

        user.getSkills().addAll(skillsToAdd); // Add only the new skills to the user's skills

        user = this.userRepository.save(user); // Save the updated user with new skills

        return ResponseEntity.ok(skillsToAdd);
    }







    @PostMapping("/remove-updated-skill")
    public ResponseEntity<String> removeSkillFromCurrentUser(@RequestParam("skillName") String skillName, Principal principal) {

        System.out.println("Skill is need to remove from current user"+skillName);

        String username = principal.getName();
        User user = userRepository.findByEmail(username);

        if (user != null) {
            Skill skillToRemove = repository.findByName(skillName);
            if (skillToRemove != null) {
                user.getSkills().remove(skillToRemove);
                userRepository.save(user);
                return ResponseEntity.ok("Skill removed successfully.");
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to remove skill.");
    }








}
