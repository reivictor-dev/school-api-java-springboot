package com.school.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.controllers.StudentController;
import com.school.data.vo.v1.StudentVO;
import com.school.exceptions.RequiredObjectIsNullException;
import com.school.exceptions.ResourceNotFoundException;
import com.school.mapper.StudentMapper;
import com.school.model.Student;
import com.school.repository.StudentRepository;

@Service
public class StudentServices {
    private Logger logger = Logger.getLogger(StudentServices.class.getName());

    @Autowired
    StudentRepository repository;

    // findAll
    public List<StudentVO> findAll() {
        logger.info("Finding all students!");

        var students = StudentMapper.INSTANCE.toVOList(repository.findAll());
        students
                .stream()
                .forEach(s -> {
                    try {
                        s.add(linkTo(methodOn(StudentController.class).findById(s.getKey())).withSelfRel());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        return students;

    }

    // findById
    public StudentVO findById(Long id) {
        logger.info("Finding a student!");

        Student entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found is this ID!"));
        var student = StudentMapper.INSTANCE.toVO(entity);
        student.add(linkTo(methodOn(StudentController.class).findById(id)).withSelfRel());
        return student;

    }

    // create
    public StudentVO create(StudentVO student) {
        logger.info("Adding new student");

        var entity = StudentMapper.INSTANCE.toEntity(student);
        var newStudent = StudentMapper.INSTANCE.toVO(repository.save(entity));
        newStudent.add(linkTo(methodOn(StudentController.class).findById(student.getKey())).withSelfRel());

        return newStudent;
    }

    // update
    public StudentVO update(StudentVO student) {
        if (student == null) {
            throw new RequiredObjectIsNullException();
        }
        var entity = repository.findById(student.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found is this ID!"));
        entity.setFirstName(student.getFirstName());
        entity.setLastName(student.getLastName());
        entity.setEmail(student.getEmail());
        entity.setAge(student.getAge());
        entity.setWeight(student.getWeight());
        entity.setHeight(student.getHeight());

        var vo = StudentMapper.INSTANCE.toVO(repository.save(entity));
        vo.add(linkTo(methodOn(StudentController.class).findById(student.getKey())).withSelfRel());
        return vo;
    }

    // delete
    public void delete(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found is this ID!"));
        repository.delete(entity);
    }
}
