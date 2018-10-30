package com.freshvotes.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.freshvotes.domain.User;

public class CustomSecurityUser extends User implements UserDetails {

  private static final long serialVersionUID = -8351482943638214015L;
  Set<Authority> authorities = new HashSet<>();
  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return this.getPassword();
  }

  @Override
  public String getUsername() {
    return this.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
