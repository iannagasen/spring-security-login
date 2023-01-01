package com.example.security_sample_proj.securitysampleproj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.security_sample_proj.securitysampleproj.domain.Product;
import com.example.security_sample_proj.securitysampleproj.repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public List<Product> findAll() {
    return productRepository.findAll();
  }
}
