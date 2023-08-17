package com.school.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.controllers.GradeController;
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
        grades
                .stream()
                .forEach(l -> {
                    try {
                        l.add(linkTo(methodOn(GradeController.class).findById(l.getKey())).withSelfRel());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        return grades;
    }

    public GradeVO findById(Long id) throws Exception {
        var grade = gradeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID"));
        var vo = GradeMapper.INSTANCE.toVO(grade);
        vo.add(linkTo(methodOn(GradeController.class).findById(id)).withSelfRel());
        return vo;
    }

    public GradeVO createGradeByStudentId(Long studentId, GradeVO grade) throws Exception {
        var student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID"));

        var gradeEntity = GradeMapper.INSTANCE.toEntity(grade);
        gradeEntity.setStudent(student);

        var savedGrade = gradeRepository.save(gradeEntity);
        var vo = GradeMapper.INSTANCE.toVO(savedGrade);
        vo.add(linkTo(methodOn(GradeController.class).findById(grade.getKey())).withSelfRel());
        return vo;

    }

    public GradeVO updateGrade(Long studentId, Long gradeId, GradeVO updatedGradeVO) throws Exception {
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
        vo.add(linkTo(methodOn(GradeController.class).findById(gradeId)).withSelfRel());
        return vo;
    }

    public void delete(Long id) {
        var entity = gradeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        gradeRepository.delete(entity);
    }

}
