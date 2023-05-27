package com.institute.journal.controller;

import com.institute.journal.domain.user.User;
import com.institute.journal.dto.user.UserDto;
import com.institute.journal.mapper.UserMapper;
import com.institute.journal.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    @ResponseBody
    @PreAuthorize("hasRole('TEACHER')")
    public List<UserDto> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDto> userDtoList = new ArrayList<>();

        for (User user : users) {
            userDtoList.add(userMapper.fromObjectToDto(user));
        }

        return userDtoList;
    }

    @PostMapping("/findByEmail")
    @ResponseBody
    @PreAuthorize("hasRole('TEACHER')")
    public UserDto getUserByEmail(@RequestBody String email) {
        User user = userService.getUserByEmail(email);
        if(user != null){
            return userMapper.fromObjectToDto(user);
        }else{
            return null;
        }
    }

    @PostMapping("/save")
    @ResponseBody
    @PreAuthorize("hasRole('TEACHER') or hasRole('PARENT')")
    public UserDto saveUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        return userMapper.fromObjectToDto(updatedUser);
    }
}
