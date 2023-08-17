package com.school.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.data.vo.v1.GradeVO;
import com.school.exceptions.ResourceNotFoundException;
import com.school.mapper.GradeMapper;
import com.school.repository.GradeRepository;
import com.school.repository.StudentRepository;

@Service
public class GradeServices {

    private Logger logger = Logger.getLogger(GradeServices.class.getName());
    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    StudentRepository studentRepository;

    public List<GradeVO> findAll() {
        logger.info("Searching all grades!");
        var grades = GradeMapper.INSTANCE.toVOList(gradeRepository.findAll());
        return grades;
    }

    public GradeVO findById(Long id) throws Exception {
        var grade = gradeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID"));
        var vo = GradeMapper.INSTANCE.toVO(grade);
        return vo;
    }

    public GradeVO createGradeByStudentId(Long studentId, GradeVO grade) {
        var student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID"));

        var gradeEntity = GradeMapper.INSTANCE.toEntity(grade);
        gradeEntity.setStudent(student);

        var savedGrade = gradeRepository.save(gradeEntity);
        return GradeMapper.INSTANCE.toVO(savedGrade);

    }

    public GradeVO updateGrade(Long studentId, Long gradeId, GradeVO updatedGradeVO) {
        var gradeEntity = gradeRepository.findById(gradeId)
                .orElseThrow(() -> new ResourceNotFoundException("No grade founded for this ID!"));

        var studentEntity = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("No student founded for this ID!"));

        gradeEntity.setMath(updatedGradeVO.getMath());
        gradeEntity.setChemistry(updatedGradeVO.getChemistry());
        gradeEntity.setScience(updatedGradeVO.getScience());
        gradeEntity.setEnglish(updatedGradeVO.getEnglish());
        studentEntity.getGrades().add(gradeEntity);

        var vo = GradeMapper.INSTANCE.toVO(gradeRepository.save(gradeEntity));

        return vo;
    }

    public void delete(Long id) {
        var entity = gradeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        gradeRepository.delete(entity);
    }

}
