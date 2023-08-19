package com.school.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.school.config.ResponseFile;
import com.school.model.ProfilePicture;
import com.school.services.ProfilePictureServices;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/uploadFile/v1")
public class ProfilePictureController {

        @Autowired
        private ProfilePictureServices services;

        @PostMapping("/uploadProfilePicture/{studentId}")
        public ResponseFile uploadProfilePicture(
                        @RequestParam("file") MultipartFile file,
                        @PathVariable("studentId") Long studentId) {
                ProfilePicture picture = services.storeFile(file, studentId);
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path("/api/uploadFile/v1/downloadFile/")
                                .path(picture.getFileName())
                                .toUriString();

                return new ResponseFile(picture.getFileName(), fileDownloadUri, file.getContentType(),
                                file.getSize(), studentId);
        }

        @GetMapping("/downloadFile/{fileName:.+}")
        public ResponseEntity<ByteArrayResource> downloadFile(
                        @PathVariable String fileName, HttpServletRequest request) {
                ProfilePicture filePicture = services.getFile(fileName);

                return ResponseEntity.ok()
                                .contentType(MediaType.parseMediaType(filePicture.getFileType()))
                                .header(HttpHeaders.CONTENT_DISPOSITION,
                                                "attachment; filename=\"" + filePicture.getFileName() + "\"")
                                .body(new ByteArrayResource(filePicture.getData()));

        }
}
