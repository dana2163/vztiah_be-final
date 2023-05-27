package com.institute.journal.mapper;

import com.institute.journal.domain.Grade;
import com.institute.journal.domain.Student;
import com.institute.journal.dto.grade.GradeCreateDto;
import com.institute.journal.dto.grade.GradeDto;
import com.institute.journal.dto.student.StudentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GradeMapper {

    private final UserMapper userMapper;
    private final StudentMapper studentMapper;

    public Grade fromDtoToObject(GradeCreateDto gradeCreateDto) {
        return new Grade(gradeCreateDto.getName());
    }

    public GradeDto fromObjectToDto(Grade grade) {
        List<StudentDto> studentDtoList = new ArrayList<>();

        for (Student student : grade.getStudents()) {
            studentDtoList.add(studentMapper.fromObjectToDto(student));
        }

        return new GradeDto(
                grade.getId(),
                grade.getName(),
                userMapper.fromObjectToDto(grade.getTeacher()),
                studentDtoList);
    }
}
