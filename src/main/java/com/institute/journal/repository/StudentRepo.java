package com.institute.journal.repository;

import com.institute.journal.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Long> {
    List<Student> findByParent_Id(Long id);

}
