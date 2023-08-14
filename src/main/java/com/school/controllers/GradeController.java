package com.school.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
