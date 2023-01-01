package com.example.security_sample_proj.securitysampleproj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security_sample_proj.securitysampleproj.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findUserByUsername(String username);

}
