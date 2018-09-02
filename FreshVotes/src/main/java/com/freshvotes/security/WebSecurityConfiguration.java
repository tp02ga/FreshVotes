package com.freshvotes.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("trevor@craftycodr.com")
        .password("asdfasdf")
        .roles("USER");
  }
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests()
          .antMatchers("/").permitAll()
          .antMatchers("/login").permitAll()
          .anyRequest().hasRole("USER").and()
//        .formLogin()
//          .loginPage("/login")
//          .permitAll()
//          .and()
        .logout()
          .logoutUrl("/logout")
          .permitAll();
  }
}
