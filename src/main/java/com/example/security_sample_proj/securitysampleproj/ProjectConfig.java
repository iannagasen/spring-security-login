package com.example.security_sample_proj.securitysampleproj;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.security_sample_proj.securitysampleproj.service.AuthenticationProviderService;

@Configuration
public class ProjectConfig {

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SCryptPasswordEncoder sCryptPasswordEncoder() {
    return new SCryptPasswordEncoder(16384, 8, 1, 32, 64);
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http,
      AuthenticationProviderService authenticationProviderService) throws Exception {
    return http
        .authenticationProvider(authenticationProviderService)
        .formLogin(form -> form.defaultSuccessUrl("/main", true))
        .authorizeHttpRequests(authz -> authz.anyRequest().authenticated())
        .build();
  }

}
