package com.freshvotes.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.freshvotes.security.Authority;

@Entity
@Table(name="users")
public class User
{
  private Long id;
  private String username;
  private String password;
  private String name;
  private Set<Authority> authorities = new HashSet<>();
  private Set<Product> products = new HashSet<>();
  
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  public Long getId()
  {
    return id;
  }
  public void setId(Long id)
  {
    this.id = id;
  }
  public String getUsername()
  {
    return username;
  }
  public void setUsername(String username)
  {
    this.username = username;
  }
  public String getPassword()
  {
    return password;
  }
  public void setPassword(String password)
  {
    this.password = password;
  }
  public String getName()
  {
    return name;
  }
  public void setName(String name)
  {
    this.name = name;
  }
  @OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY, mappedBy="user")
  public Set<Product> getProducts() {
    return products;
  }
  public void setProducts(Set<Product> products) {
    this.products = products;
  }
  @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="user")
  public Set<Authority> getAuthorities() {
    return authorities;
  }
  public void setAuthorities(Set<Authority> authorities) {
    this.authorities = authorities;
  }
  @Override
  public String toString() {
    return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", authorities="
        + authorities + "]";
  }
}
