package com.school.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.data.vo.v1.GradeVO;
import com.school.services.GradeServices;

@RestController
@RequestMapping("/api/grade/v1")
public class GradeController {

    @Autowired
    GradeServices services;

    @GetMapping()
    public List<GradeVO> findAll() {
        return services.findAll();
    }

    @GetMapping("/{id}")
    public GradeVO findById(@PathVariable(value = "id") Long id) throws Exception {
        return services.findById(id);
    }

    @PostMapping("/create/{studentId}")
    public GradeVO create(
            @PathVariable Long studentId,
            @RequestBody GradeVO grade) throws Exception {
        return services.createGradeByStudentId(studentId, grade);
    }

    @PutMapping("/{studentId}/update/{gradeId}")
    public GradeVO updateGrade(
            @PathVariable Long studentId,
            @PathVariable Long gradeId,
            @RequestBody GradeVO updatedGradeVO) throws Exception {

        GradeVO updatedGrade = services.updateGrade(studentId, gradeId, updatedGradeVO);
        return updatedGrade;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }
}
