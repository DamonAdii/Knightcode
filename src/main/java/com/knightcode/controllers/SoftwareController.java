package com.knightcode.controllers;

import com.knightcode.model.Software;
import com.knightcode.model.User;
import com.knightcode.repository.*;
import com.knightcode.service.impl.SoftwareServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SoftwareController {

    private final SoftwareRepository repository;

    private final UserRepository userRepository;

    private final AboutRepository aboutRepository;

    private final EducationRepository educationRepository;

    private final ExperienceRepository experienceRepository;

    private final ProjectRepository projectRepository;

    private final SoftwareServiceImpl softwareService;


    @GetMapping("/search-softwares")
    public List<Software> searchSoftwares(@RequestParam("softwareName") String softwareName) {
        return softwareService.searchSoftwares(softwareName);
    }





    @PostMapping("/update-softwares")
    public ResponseEntity<List<Software>> updateSoftwares(@RequestBody List<String> selectedSoftwares, Principal principal) {
        log.info("Received selectedSoftwares: " + selectedSoftwares);

        String username = principal.getName();
        User user = this.userRepository.findByEmail(username);

        List<Software> softwaresToAdd = new ArrayList<>();

        for (String softwareName : selectedSoftwares) {
            Software software = this.repository.findByName(softwareName);
            if (software != null && !user.getSoftwares().contains(software)) {
                softwaresToAdd.add(software);
            }
        }

        user.getSoftwares().addAll(softwaresToAdd); // Add only the new skills to the user's skills

        user = this.userRepository.save(user); // Save the updated user with new skills

        return ResponseEntity.ok(softwaresToAdd);
    }







    @PostMapping("/remove-updated-software")
    public ResponseEntity<String> removeSoftwareFromCurrentUser(@RequestParam("softwareName") String softwareName, Principal principal) {

        System.out.println("Software is need to remove from current user"+softwareName);

        String username = principal.getName();
        User user = userRepository.findByEmail(username);

        if (user != null) {
            Software softwareToRemove = repository.findByName(softwareName);
            if (softwareToRemove != null) {
                user.getSoftwares().remove(softwareToRemove);
                userRepository.save(user);
                return ResponseEntity.ok("Software removed successfully.");
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to remove software.");
    }


}
