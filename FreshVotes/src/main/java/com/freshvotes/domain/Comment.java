package com.freshvotes.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Comment
{
  private CommentId pk;
  private String text;
  
  @EmbeddedId
  public CommentId getPk()
  {
    return pk;
  }
  public void setPk(CommentId pk)
  {
    this.pk = pk;
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
}
