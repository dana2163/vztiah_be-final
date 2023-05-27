package com.institute.journal.repository;

import com.institute.journal.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepo extends JpaRepository<Grade, Long> {
    List<Grade> findByTeacherId(Long id);
}
