package com.knightcode.controllers;

import com.knightcode.model.User;
import com.knightcode.repository.FollowRepository;
import com.knightcode.repository.UserRepository;
import com.knightcode.service.impl.FollowService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FollowUserController {

    private final UserRepository userRepository;

    private final EntityManager entityManager;

    private final FollowRepository followRepository;

    private final FollowService followService;

    @PostMapping("/follow/{userId}")
    public ResponseEntity<String> followUser(@PathVariable Long userId, Principal principal) {
        // Get the currently logged-in user (you need to implement your own authentication mechanism)
        String username = principal.getName();
        User currentUser = this.userRepository.findByEmail(username); // Replace with your authentication logic

        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
        }

        long currentUserId = currentUser.getId();
        long toFollowId = userId;

        // Check if the user is trying to follow themselves
        if (currentUserId == toFollowId) {
            return ResponseEntity.badRequest().body("Cannot follow yourself.");
        }

        User ToFollowUser = this.userRepository.findById(userId).orElse(null);

        // Check if the user is already following the target user
        if (followService.isFollowing(currentUser, ToFollowUser)) {
            return ResponseEntity.badRequest().body("You are already following this user.");
        }

        // Follow the user
        followService.follow(currentUser, userId);
        return ResponseEntity.ok("Followed user with ID: " + userId);
    }


    @PostMapping("/unfollow/{userId}")
    public ResponseEntity<String> unfollowUser(@PathVariable Long userId,Principal principal) {
        // Get the currently logged-in user (you need to implement your own authentication mechanism)
        String username =  principal.getName();
        User currentUser = this.userRepository.findByEmail(username); // Replace with your authentication logic

        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
        }

        // Unfollow the user
        followService.unfollow(currentUser, userId);
        return ResponseEntity.ok("Unfollowed user with ID: " + userId);
    }



}
