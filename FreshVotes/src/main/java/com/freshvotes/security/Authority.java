package com.freshvotes.security;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

  private static final long serialVersionUID = 1272548942962614584L;
  private String authority;
  
  @Override
  public String getAuthority() {
    return this.authority;
  }

  public void setAuthority(String authority) {
    this.authority = authority;
  }

}
