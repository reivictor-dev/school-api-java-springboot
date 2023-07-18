package com.school.integrationtests.controller;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.integrationtests.testcontainers.AbstractIntegrationTest;
import com.school.integrationtests.vo.StudentVO;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest extends AbstractIntegrationTest {
    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;

    private static StudentVO student;

    @BeforeAll
    public static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        student = new StudentVO();
    }

    @Test
    @Order(1)
    public void testCreate() throws JsonMappingException, JsonProcessingException {
        mockStudent();

        specification = new RequestSpecBuilder()
                .setBasePath("/api/students/v1")
                .setPort(8888)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = given().spec(specification)
                .contentType("application/json")
                .body(student)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        StudentVO createdStudent = objectMapper.readValue(content, StudentVO.class);
        student = createdStudent;

        assertNotNull(createdStudent);

        assertNotNull(createdStudent.getId());
        assertNotNull(createdStudent.getFirstName());
        assertNotNull(createdStudent.getLastName());
        assertNotNull(createdStudent.getEmail());
        assertNotNull(createdStudent.getAge());
        assertNotNull(createdStudent.getWeight());
        assertNotNull(createdStudent.getHeight());

        assertTrue(createdStudent.getId() > 0);

        assertEquals("Ronaldinho", createdStudent.getFirstName());
        assertEquals("Gaucho", createdStudent.getLastName());
        assertEquals("ronaldinho@teste.com", createdStudent.getEmail());
        assertEquals(17, createdStudent.getAge());
        assertEquals(78.5, createdStudent.getWeight());
        assertEquals(1.70, createdStudent.getHeight());

    }

    @Test
    @Order(2)
    public void testFindById() throws JsonMappingException,
            JsonProcessingException {
        mockStudent();

        specification = new RequestSpecBuilder()
                .setBasePath("/api/students/v1")
                .setPort(8888)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        var content = given().spec(specification)
                .contentType("application/json")
                .pathParam("id", student.getId())
                .when()
                .get("{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        StudentVO createdStudent = objectMapper.readValue(content, StudentVO.class);
        student = createdStudent;

        assertNotNull(createdStudent);

        assertNotNull(createdStudent.getId());
        assertNotNull(createdStudent.getFirstName());
        assertNotNull(createdStudent.getLastName());
        assertNotNull(createdStudent.getEmail());
        assertNotNull(createdStudent.getAge());
        assertNotNull(createdStudent.getWeight());
        assertNotNull(createdStudent.getHeight());

        assertTrue(createdStudent.getId() > 0);

        assertEquals("Ronaldinho", createdStudent.getFirstName());
        assertEquals("Gaucho", createdStudent.getLastName());
        assertEquals("ronaldinho@teste.com", createdStudent.getEmail());
        assertEquals(17, createdStudent.getAge());
        assertEquals(78.5, createdStudent.getWeight());
        assertEquals(1.70, createdStudent.getHeight());
    }

    public void mockStudent() {
        student.setFirstName("Ronaldinho");
        student.setLastName("Gaucho");
        student.setEmail("ronaldinho@test.com");
        student.setAge(17);
        student.setWeight(78.5);
        student.setHeight(1.70);
    }
}