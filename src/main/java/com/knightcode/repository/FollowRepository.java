package com.knightcode.repository;

import com.knightcode.model.Follow;
import com.knightcode.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow,Long> {
    Follow findByFollowerAndFollowed(User follower, User followed);

}
