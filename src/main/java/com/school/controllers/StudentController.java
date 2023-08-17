package com.school.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.school.data.vo.v1.StudentVO;
import com.school.model.Student;
import com.school.services.StudentServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/students/v1")
@Tag(name = "Student", description = "Endpoints for managing Students")
public class StudentController {

        @Autowired
        private StudentServices service;

        @GetMapping()
        @Operation(summary = "Find all Students", description = "Find all Students", tags = { "Student" }, responses = {
                        @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Student.class))),
                        @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
        })
        public ResponseEntity<PagedModel<EntityModel<StudentVO>>> findAll(
                        @RequestParam(value = "page", defaultValue = "0") Integer page,
                        @RequestParam(value = "size", defaultValue = "10") Integer size,
                        @RequestParam(value = "direction", defaultValue = "asc") String direction) {
                var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
                Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "first_name"));
                return ResponseEntity.ok(service.findAll(pageable));
        }

        @GetMapping(value = "/{id}")
        @Operation(summary = "Find a Student", description = "Find a Student by ID", tags = { "Student" }, responses = {
                        @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Student.class))),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
        })
        public StudentVO findById(@PathVariable(value = "id") Long id) {
                return service.findById(id);
        }

        @PostMapping()
        @Operation(summary = "Create a Student", description = "Adds a new Student passing JSON in body", tags = {
                        "Student" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Student.class))),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
                        })
        public StudentVO create(@RequestBody StudentVO student) {
                return service.create(student);
        }

        @PutMapping()
        @Operation(summary = "Updates a Student", description = "Updates a Student passing JSON in body", tags = {
                        "Student" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Student.class))),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
                        })
        public StudentVO update(@RequestBody StudentVO student) {
                return service.update(student);
        }

        @DeleteMapping(value = "/{id}")
        @Operation(summary = "Deletes a Student", description = "Deletes a Student by ID", tags = {
                        "Student" }, responses = {
                                        @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Student.class))),
                                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                                        @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                                        @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
                        })
        public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
                service.delete(id);
                return ResponseEntity.noContent().build();
        }

}
