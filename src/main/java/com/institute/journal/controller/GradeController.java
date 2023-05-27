package com.institute.journal.controller;

import com.institute.journal.domain.Grade;
import com.institute.journal.domain.Student;
import com.institute.journal.dto.grade.GradeCreateDto;
import com.institute.journal.dto.student.StudentCreateDto;
import com.institute.journal.dto.grade.GradeDto;
import com.institute.journal.mapper.GradeMapper;
import com.institute.journal.mapper.StudentMapper;
import com.institute.journal.service.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/grades")
public class GradeController {

    private final GradeService gradeService;
    private final GradeMapper gradeMapper;
    private final StudentMapper studentMapper;

    @PostMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('grade:write')")
    public GradeDto createGrade(@RequestBody GradeCreateDto gradeCreateDto) {
        Grade grade = gradeMapper.fromDtoToObject(gradeCreateDto);
        Grade savedGrade = gradeService.createGrave(grade);
        return gradeMapper.fromObjectToDto(savedGrade);
    }

    @GetMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('grade:read')")
    public List<GradeDto> getGradesOfTeacher() {
        List<GradeDto> gradeDtoList = new ArrayList<>();
        List<Grade> grades = gradeService.getGradesOfTeacher();


        for (Grade grade : grades) {
            gradeDtoList.add(gradeMapper.fromObjectToDto(grade));
        }
        return gradeDtoList;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('grade:read')")
    @ResponseBody
    public GradeDto getGradeById(@PathVariable Long id) {
        Grade grade = gradeService.getGradeById(id);
        return gradeMapper.fromObjectToDto(grade);
    }

    @PostMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('grade:write')")
    public void addStudentToGrade(@PathVariable Long id,
                                       @RequestBody StudentCreateDto studentCreateDto) {
        Student student = studentMapper.fromDtoToObject(studentCreateDto);
        gradeService.addStudentToGrade(student, id);
    }

    @DeleteMapping("/{gradeId}")
    @PreAuthorize("hasAuthority('student:delete')")
    public void deleteStudent(@PathVariable Long gradeId,
                              @RequestParam Long studentId) {
        gradeService.removeStudent(gradeId, studentId);
    }
}
