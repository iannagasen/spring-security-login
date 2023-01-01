package com.example.security_sample_proj.securitysampleproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security_sample_proj.securitysampleproj.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
