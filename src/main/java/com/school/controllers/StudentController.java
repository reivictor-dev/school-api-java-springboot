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

import com.school.data.vo.v1.StudentVO;
import com.school.services.StudentServices;

@RestController
@RequestMapping("/api/students/v1")
public class StudentController {

    @Autowired
    private StudentServices service;

    @GetMapping()
    public List<StudentVO> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public StudentVO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping()
    public StudentVO create(@RequestBody StudentVO student) {
        return service.create(student);
    }

    @PutMapping()
    public StudentVO update(@RequestBody StudentVO student) {
        return service.update(student);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
