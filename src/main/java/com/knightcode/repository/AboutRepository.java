package com.knightcode.repository;

import com.knightcode.model.About;
import com.knightcode.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AboutRepository extends JpaRepository<About,Long> {

   public About findByUser(User user);

}
