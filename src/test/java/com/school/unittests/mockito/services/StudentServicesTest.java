package com.school.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.school.data.vo.v1.StudentVO;
import com.school.model.Student;
import com.school.repository.StudentRepository;
import com.school.services.StudentServices;
import com.school.unittests.mapper.MockStudent;

public class StudentServicesTest {

    MockStudent input;

    @InjectMocks
    private StudentServices services;

    @Mock
    StudentRepository repository;

    @BeforeEach
    void setUpMocks() {
        input = new MockStudent();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Student entity = input.mockEntity(1);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var student = services.findById(1L);

        assertNotNull(student);
        assertNotNull(student.getKey());
        assertNotNull(student.getLinks());
        assertTrue(student.toString().contains("links: [</api/students/v1/1>;rel=\"self\"]"));
        assertEquals("First Name test1", student.getFirstName());
        assertEquals("Last Name test1", student.getLastName());
        assertEquals(1, student.getAge());
        assertEquals(1D, student.getWeight());
        assertEquals(1D, student.getHeight());
    }

    @Test
    void testCreate() {
        Student entity = input.mockEntity(1);

        Student persisted = entity;
        persisted.setId(1L);

        StudentVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.save(entity)).thenReturn(persisted);

        var student = services.create(vo);

        assertNotNull(student);
        assertNotNull(student.getKey());
        assertNotNull(student.getLinks());
        assertTrue(student.toString().contains("links: [</api/students/v1/1>;rel=\"self\"]"));
        assertEquals("First Name test1", student.getFirstName());
        assertEquals("Last Name test1", student.getLastName());
        assertEquals(1, student.getAge());
        assertEquals(1D, student.getWeight());
        assertEquals(1D, student.getHeight());
    }

    @Test
    void testUpdate() {
        Student entity = input.mockEntity(1);
        entity.setId(1L);

        Student persisted = entity;
        persisted.setId(1L);

        StudentVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var student = services.update(vo);

        assertNotNull(student);
        assertNotNull(student.getKey());
        assertNotNull(student.getLinks());
        assertTrue(student.toString().contains("links: [</api/students/v1/1>;rel=\"self\"]"));
        assertEquals("First Name test1", student.getFirstName());
        assertEquals("Last Name test1", student.getLastName());
        assertEquals(1, student.getAge());
        assertEquals(1D, student.getWeight());
        assertEquals(1D, student.getHeight());
    }

    @Test
    void testDelete() {
        Student entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        services.delete(1L);
    }
}
