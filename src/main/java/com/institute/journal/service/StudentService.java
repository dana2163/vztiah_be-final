package com.institute.journal.service;

import com.institute.journal.domain.Student;
import com.institute.journal.domain.user.User;
import com.institute.journal.repository.StudentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepo studentRepo;

    public List<Student> getStudentOfParent(User parent) {
        return studentRepo.findByParent_Id(parent.getId());
    }

}
