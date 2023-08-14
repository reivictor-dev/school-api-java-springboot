package com.school.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.data.vo.v1.GradeVO;
import com.school.mapper.GradeMapper;
import com.school.repository.GradeRepository;

@Service
public class GradeServices {

    @Autowired
    GradeRepository repository;

    public List<GradeVO> findAll() {

        var grades = GradeMapper.INSTANCE.toVOList(repository.findAll());
        return grades;
    }
}
