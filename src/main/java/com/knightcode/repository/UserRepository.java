package com.knightcode.repository;

import com.knightcode.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    public User findByEmail(String email);

    List<User> findAllByIdNot(Long currentUserId);

    User findByEmailIgnoreCase(String email);


}
