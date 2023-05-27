package com.institute.journal.controller;

import com.institute.journal.domain.Student;
import com.institute.journal.domain.user.User;
import com.institute.journal.dto.student.StudentDto;
import com.institute.journal.mapper.StudentMapper;
import com.institute.journal.service.StudentService;
import com.institute.journal.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/parents")
public class ParentController {

    private final UserService userService;
    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @GetMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('student:read')")
    public List<StudentDto> getStudentInfo() {
        User user = userService.getCurrentUser();
        List<Student> students = studentService.getStudentOfParent(user);
        List<StudentDto> studentDtoList = new ArrayList<>();
        for (Student student : students) {
            studentDtoList.add(studentMapper.fromObjectToDto(student));
        }
        return studentDtoList;
    }
}
