package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.school.model.auth.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT e FROM User e WHERE e.email =:email")
    User findByEmail(@Param("email") String email);
}
