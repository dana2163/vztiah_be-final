package com.institute.journal.dto.user;

import com.institute.journal.domain.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private UserRole role;
}
