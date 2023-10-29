package com.knightcode.service.impl;

import com.knightcode.model.Follow;
import com.knightcode.model.User;
import com.knightcode.repository.FollowRepository;
import com.knightcode.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    private final UserRepository userRepository;


    public void follow(User currentUser, Long userId) {

        System.out.println("Current User got : "+currentUser.getId());
        System.out.println("To follow User got : "+userId);

        User followed = userRepository.findById(userId).orElse(null);
        if (followed != null) {
            Follow follow = new Follow();
            follow.setFollower(currentUser);
            follow.setFollowed(followed);
            followRepository.save(follow);
        }

    }



    public void unfollow(User currentUser, Long followedUserId) {
        User followed = userRepository.findById(followedUserId).orElse(null);
        if (followed != null) {
            Follow follow = followRepository.findByFollowerAndFollowed(currentUser, followed);
            if (follow != null) {
                followRepository.delete(follow);
            }
        }
    }





    public boolean isFollowing(User currentUser, User followed) {
        // Check if follower is following followed
        Follow follow = followRepository.findByFollowerAndFollowed(currentUser, followed);
        return follow != null;
    }






}
