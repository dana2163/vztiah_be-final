package com.institute.journal.dto.student;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentCreateDto {
    private String name;
    private String surname;
    private Long parentId;
}
