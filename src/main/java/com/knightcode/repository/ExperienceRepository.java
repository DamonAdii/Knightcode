package com.knightcode.repository;

import com.knightcode.model.Experience;
import com.knightcode.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<Experience,Long> {

    public Experience findByUser(User user);

}
