package com.school.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.school.exceptions.FileStorageException;
import com.school.exceptions.ResourceNotFoundException;
import com.school.model.ProfilePicture;
import com.school.model.Student;
import com.school.repository.ProfilePictureRepository;
import com.school.repository.StudentRepository;

@Service
public class ProfilePictureServices {

    @Autowired
    ProfilePictureRepository repository;

    @Autowired
    StudentRepository studentRepository;

    public ProfilePicture storeFile(MultipartFile file, Long studentId) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("No records found is this ID!"));

        Optional<ProfilePicture> existingProfilePictureOptional = repository.findByFileName(fileName);
        if (existingProfilePictureOptional.isPresent()) {
            ProfilePicture existingProfilePicture = existingProfilePictureOptional.get();
            if (!existingProfilePicture.getStudent().getId().equals(studentId)) {
                throw new FileStorageException(
                        "A profile picture with the same file name already exists, please rename the file!");
            }
        }
        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence" + fileName);
            }

            ProfilePicture profilePicture = new ProfilePicture(fileName, file.getContentType(),
                    file.getBytes(),
                    student);

            return repository.save(profilePicture);
        } catch (Exception ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public ProfilePicture getFile(String fileName) {
        return repository.findByFileName(fileName)
                .orElseThrow(() -> new ResourceNotFoundException("No records found is this ID!"));
    }
}
