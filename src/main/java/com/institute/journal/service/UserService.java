package com.institute.journal.service;

import com.institute.journal.config.jwt.JwtTokenProvider;
import com.institute.journal.domain.user.User;
import com.institute.journal.exception.InvalidJwtTokenException;
import com.institute.journal.exception.UserAlreadyExistsException;
import com.institute.journal.exception.UserDoesNotExistException;
import com.institute.journal.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public String login(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            return jwtTokenProvider.createToken(email, getUserByEmail(email).getRole());
        } catch (AuthenticationException e) {
            throw new InvalidJwtTokenException("Invalid email or password");
        }
    }

    public String refresh(String email) {
        return jwtTokenProvider.createToken(email, getUserByEmail(email).getRole());
    }

    public User saveNewUser(User user) {
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(String.format("User with email '%s' already exist", user.getEmail()));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElse(null);
    }

    public User getCurrentUser() {
        return userRepo.findByEmail(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail()).get();
    }

    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new UserDoesNotExistException(String.format("User with id '%s' was not found", id)));
    }

    public User updateUser(User user){
        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
