package com.institute.journal.controller;

import com.institute.journal.domain.user.User;
import com.institute.journal.dto.user.authentication.UserAuthenticationRequestDto;
import com.institute.journal.dto.user.authentication.UserAuthenticationResponseDto;
import com.institute.journal.dto.user.registration.UserRegistrationRequestDto;
import com.institute.journal.mapper.UserMapper;
import com.institute.journal.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserMapper userRegistrationMapper;
    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody UserRegistrationRequestDto userRegistrationDto) {
        User user = userRegistrationMapper.fromDtoToObject(userRegistrationDto);

        User savedUser = userService.saveNewUser(user);
        return ResponseEntity.ok(userRegistrationMapper.fromObjectToDto(savedUser));
    }

    @PostMapping("/login")
    @ResponseBody
    public UserAuthenticationResponseDto login(@RequestBody UserAuthenticationRequestDto userDto) {

        UserAuthenticationResponseDto userResponseDto = new UserAuthenticationResponseDto();
        userResponseDto.setAccessToken(userService.login(userDto.getEmail(), userDto.getPassword()));
        return userResponseDto;
    }

    @GetMapping("/refresh")
    @PreAuthorize("hasRole('TEACHER') or hasRole('PARENT')")
    public String refresh(HttpServletRequest req) {
        return userService.refresh(req.getRemoteUser());
    }

    @GetMapping("/current")
    @ResponseBody
    @PreAuthorize("hasRole('TEACHER') or hasRole('PARENT')")
    public User getCurrentUser() {
        return userService.getCurrentUser();
    }
}
