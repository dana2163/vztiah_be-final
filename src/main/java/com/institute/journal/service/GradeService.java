package com.institute.journal.service;

import com.institute.journal.domain.Grade;
import com.institute.journal.domain.Student;
import com.institute.journal.domain.user.User;
import com.institute.journal.exception.GradeDoesNotExistException;
import com.institute.journal.repository.GradeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GradeService {

    private final GradeRepo gradeRepo;
    private final UserService userService;

    public Grade createGrave(Grade grade) {
        User teacher = userService.getCurrentUser();
        grade.setTeacher(teacher);
        return gradeRepo.save(grade);
    }

    public List<Grade> getGradesOfTeacher() {
        User teacher = userService.getCurrentUser();
        return gradeRepo.findByTeacherId(teacher.getId());
    }

    public void addStudentToGrade(Student student, Long gradeId) {
        Grade grade = gradeRepo.findById(gradeId)
                .orElseThrow(() -> new GradeDoesNotExistException(String.format("Grade with id '%s' does not exist", gradeId)));

        if (!grade.getStudents().contains(student)) {
            grade.getStudents().add(student);
        }

        gradeRepo.save(grade);
    }

    public Grade getGradeById(Long id) {
        return gradeRepo.findById(id)
                .orElseThrow(() -> new GradeDoesNotExistException(String.format("Grade with id '%s' does not exist", id)));
    }

    public void removeStudent(Long gradeId, Long studentId) {
        Grade grade = gradeRepo.findById(gradeId)
                .orElseThrow(() -> new GradeDoesNotExistException(String.format("Grade with id '%s' does not exist", gradeId)));

        grade.setStudents(
                grade.getStudents()
                        .stream()
                        .filter(s -> !Objects.equals(s.getId(), studentId))
                        .collect(Collectors.toList()));

        gradeRepo.save(grade);
    }
}
