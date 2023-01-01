package com.example.security_sample_proj.securitysampleproj.service;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.security_sample_proj.securitysampleproj.domain.CustomUserDetails;
import com.example.security_sample_proj.securitysampleproj.repository.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Supplier<UsernameNotFoundException> s = () -> new UsernameNotFoundException("Problem during authentication!");
    return new CustomUserDetails(userRepository.findUserByUsername(username).orElseThrow(s));
  }

}
