package com.knightcode.security;

import com.knightcode.model.User;
import com.knightcode.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.repository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        if (!user.isEnabled()) {
            throw new DisabledException("Account is not confirmed yet");
        }

        CustomUserDetails userDetails = new CustomUserDetails(user);

        return userDetails;
    }


}
