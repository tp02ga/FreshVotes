package com.freshvotes.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Product {
  private Long id;
  private String name;
  private User user;
  private Set<Feature> features = new HashSet<>();
  private Boolean published;
  
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  
  @ManyToOne
  public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }
  
  @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="product")
  public Set<Feature> getFeatures() {
    return features;
  }
  public void setFeatures(Set<Feature> features) {
    this.features = features;
  }
  public Boolean getPublished() {
    return published;
  }
  public void setPublished(Boolean published) {
    this.published = published;
  }
  @Override
  public String toString() {
    return "Product [id=" + id + ", name=" + name + ", user=" + user + ", features=" + features + ", published="
        + published + "]";
  }
  
}
