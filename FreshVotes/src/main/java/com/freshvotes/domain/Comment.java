package com.freshvotes.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comment
{
  private Long id;  
  private String text;
  private User user;
  private Feature feature;
  private List<Comment> comments = new ArrayList<>();
  private Comment comment;
  private Date createdDate;
  
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  @Column(length=5000)
  public String getText()
  {
    return text;
  }
  public void setText(String text)
  {
    this.text = text;
  }
  
  @ManyToOne
  @JsonIgnore
  public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }
  @ManyToOne
  @JsonIgnore
  public Feature getFeature() {
    return feature;
  }
  public void setFeature(Feature feature) {
    this.feature = feature;
  }
  
  @OneToMany(mappedBy="comment")
  public List<Comment> getComments() {
    return comments;
  }
  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }
  @ManyToOne
  @JoinColumn(name="comment_id", nullable=true)
  @JsonIgnore
  public Comment getComment() {
    return comment;
  }
  public void setComment(Comment comment) {
    this.comment = comment;
  }
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
}
