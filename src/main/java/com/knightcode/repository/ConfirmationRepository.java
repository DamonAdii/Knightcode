package com.knightcode.repository;

import com.knightcode.model.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationRepository extends JpaRepository<Confirmation,Long> {

    Confirmation findByToken(String token);

}
