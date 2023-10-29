package com.knightcode.repository;

import com.knightcode.model.Education;
import com.knightcode.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education,Long> {

    public Education findByUser(User user);

}
