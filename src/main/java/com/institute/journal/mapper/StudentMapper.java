package com.institute.journal.mapper;

import com.institute.journal.domain.Student;
import com.institute.journal.domain.user.User;
import com.institute.journal.dto.student.StudentCreateDto;
import com.institute.journal.dto.student.StudentDto;
import com.institute.journal.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentMapper {

    private final UserService userService;
    private final UserMapper userMapper;

    public Student fromDtoToObject(StudentCreateDto studentCreateDto) {
        Student student = new Student(
                studentCreateDto.getName(),
                studentCreateDto.getSurname()
        );

        User user = userService.getUserById(studentCreateDto.getParentId());
        student.setParent(user);

        return student;
    }

    public StudentDto fromObjectToDto(Student student) {
        return new StudentDto(
                student.getId(),
                student.getName(),
                student.getSurname(),
                userMapper.fromObjectToDto(student.getParent())
        );
    }
}
