package com.institute.journal.dto.user.registration;

import lombok.Data;

@Data
public class UserRegistrationRequestDto {
    private String name;
    private String surname;
    private String email;
    private String password;
    private boolean isTeacher;
}
