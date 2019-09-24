package com.freshvotes.web;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freshvotes.domain.Comment;
import com.freshvotes.domain.Feature;
import com.freshvotes.domain.User;
import com.freshvotes.repositories.CommentRepository;
import com.freshvotes.repositories.FeatureRepository;

@Controller
@RequestMapping("/products/{productId}/features/{featureId}/comments")
public class CommentController {
  @Autowired
  public CommentRepository commentRepo;
  @Autowired
  public FeatureRepository featureRepo;
  
  @GetMapping("")
  @ResponseBody
  public List<Comment> getComments (@PathVariable Long featureId) {
    
    List<Comment> findByFeatureId = commentRepo.findByFeatureId(featureId);
    System.out.println(findByFeatureId);
    return findByFeatureId;
  }
  
  @PostMapping("")
  public String postComment(@AuthenticationPrincipal User user, @PathVariable Long productId,
      @PathVariable Long featureId, Comment rootComment, @RequestParam(required=false) Long parentId,
      @RequestParam(required=false) String childCommentText) {
    Optional<Feature> featureOpt = featureRepo.findById(featureId);
    // save a root level comment here
    if (!StringUtils.isEmpty(rootComment.getText())) {
      populateCommentMetadata(user, featureOpt, rootComment);
      commentRepo.save(rootComment);
    }
    // save a child level comment here 
    else if (parentId != null) {
      Comment comment = new Comment();
      Optional<Comment> parentCommentOpt = commentRepo.findById(parentId);
      if (parentCommentOpt.isPresent())
        comment.setComment(parentCommentOpt.get());
      comment.setText(childCommentText);
      populateCommentMetadata(user, featureOpt, comment);
      commentRepo.save(comment);
    } 
    
    
    
    return "redirect:/products/" + productId + "/features/" + featureId;
  }

  private void populateCommentMetadata(User user, Optional<Feature> featureOpt, Comment comment) {
    if (featureOpt.isPresent())
      comment.setFeature(featureOpt.get());
    comment.setUser(user);
    comment.setCreatedDate(new Date());
  }
}
