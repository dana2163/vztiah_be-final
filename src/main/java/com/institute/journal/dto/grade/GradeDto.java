package com.institute.journal.dto.grade;

import com.institute.journal.dto.student.StudentDto;
import com.institute.journal.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeDto {
    private Long id;
    private String name;
    private UserDto teacher;
    private List<StudentDto> students = new ArrayList<>();
}
