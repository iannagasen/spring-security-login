package com.example.security_sample_proj.securitysampleproj.service;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.security_sample_proj.securitysampleproj.domain.CustomUserDetails;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class AuthenticationProviderService implements AuthenticationProvider {

  private JpaUserDetailsService userDetailsService;
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  private SCryptPasswordEncoder sCryptPasswordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();
    CustomUserDetails user = userDetailsService.loadUserByUsername(username);
    log.info("Authenticating... username: {}, password: {}, encrypted password: {}",
        username, password, bCryptPasswordEncoder.encode(password));

    return switch (user.getUser().getAlgorithm()) {
      case BCRYPT -> checkPassword(user, password, bCryptPasswordEncoder);
      case SCRYPT -> checkPassword(user, password, sCryptPasswordEncoder);
      default -> throw new BadCredentialsException("Bad Credentials.");
    };
  }

  @Override
  public boolean supports(Class<?> authentication) {
    log.info("Checking AuthenticationProviderService.supports...");
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }

  private Authentication checkPassword(CustomUserDetails user, String rawPassword, PasswordEncoder encoder) {
    if (encoder.matches(rawPassword, user.getPassword()))
      return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
    else
      throw new BadCredentialsException("Bad Credenitals");
  }
}
