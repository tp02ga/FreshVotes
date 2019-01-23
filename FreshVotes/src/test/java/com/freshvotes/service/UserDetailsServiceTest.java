package com.freshvotes.service;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserDetailsServiceTest {

  
  @Test
  public void generate_encrypted_password() {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String rawPassword = "asdfasdf";
    String encodedPassword = encoder.encode(rawPassword);
    
    System.out.println(encodedPassword);
    
    assertThat(rawPassword, not(encodedPassword));
  }

}
