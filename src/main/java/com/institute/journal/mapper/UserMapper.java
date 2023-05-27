package com.institute.journal.mapper;

import com.institute.journal.domain.user.User;
import com.institute.journal.domain.user.UserRole;
import com.institute.journal.dto.user.UserDto;
import com.institute.journal.dto.user.registration.UserRegistrationRequestDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User fromDtoToObject(UserRegistrationRequestDto userRegistrationRequestDto) {
        User user = new User(
                userRegistrationRequestDto.getName(),
                userRegistrationRequestDto.getSurname(),
                userRegistrationRequestDto.getEmail(),
                userRegistrationRequestDto.getPassword()
        );

        user.setRole(userRegistrationRequestDto.isTeacher()
                ? UserRole.TEACHER
                : UserRole.PARENT);

        return user;
    }

    public UserDto fromObjectToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getRole()
        );
    }
}
