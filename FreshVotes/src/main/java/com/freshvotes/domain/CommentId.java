package com.freshvotes.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class CommentId implements Serializable
{
  private static final long serialVersionUID = -3690211290276882371L;
  private User user;
  private Feature feature;
  
  @ManyToOne
  public User getUser()
  {
    return user;
  }
  public void setUser(User user)
  {
    this.user = user;
  }
  @ManyToOne
  public Feature getFeature()
  {
    return feature;
  }
  public void setFeature(Feature feature)
  {
    this.feature = feature;
  }
}
