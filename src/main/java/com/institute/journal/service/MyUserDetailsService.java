package com.institute.journal.service;

import com.institute.journal.domain.user.User;
import com.institute.journal.exception.UserDoesNotExistException;
import com.institute.journal.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(s)
                .orElse(null);

        if (user == null) {
            throw new UserDoesNotExistException(String.format("User with email '%s' does not exist", s));
        }

        return user;
    }
}
