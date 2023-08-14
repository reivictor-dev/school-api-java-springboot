package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.school.model.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {

}
