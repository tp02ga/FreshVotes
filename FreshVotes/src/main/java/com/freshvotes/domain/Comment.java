package com.freshvotes.domain;

import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Comment implements Comparable<Comment>
{
  private Long id;  
  private String text;
  private User user;
  private Feature feature;
  private SortedSet<Comment> comments = new TreeSet<>();
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
  @OrderBy("createdDate, id")
  public SortedSet<Comment> getComments() {
    return comments;
  }
  public void setComments(SortedSet<Comment> comments) {
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
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Comment other = (Comment) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
  @Override
  public String toString() {
    return "Comment [id=" + id + ", text=" + text + "]";
  }
  @Override
  public int compareTo(Comment that) {
    int comparedValue = this.createdDate.compareTo(that.createdDate);
    if (comparedValue == 0) {
      comparedValue = this.id.compareTo(that.id);
    }
    return comparedValue;
  }
}
