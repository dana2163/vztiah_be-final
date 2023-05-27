package com.institute.journal.dto.user.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthenticationRequestDto {
    private String email;
    private String password;
}
