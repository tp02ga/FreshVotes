package com.freshvotes.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freshvotes.domain.Comment;
import com.freshvotes.domain.Feature;
import com.freshvotes.domain.User;
import com.freshvotes.service.FeatureService;

@Controller
@RequestMapping("/products/{productId}/features")
public class FeatureController {
  Logger log = LoggerFactory.getLogger(FeatureController.class);
  
  @Autowired
  private FeatureService featureService;
  
  @PostMapping("") // this maps to -> '/products/{productId}/features'
  public String createFeature(@AuthenticationPrincipal User user, @PathVariable Long productId) {
    Feature feature = featureService.createFeature(productId, user);
    
    return "redirect:/products/"+productId+"/features/"+feature.getId();
    
  }
  
  @GetMapping("{featureId}")
  public String getFeature(@AuthenticationPrincipal User user, ModelMap model, @PathVariable Long productId, @PathVariable Long featureId) {
    Optional<Feature> featureOpt = featureService.findById(featureId);
    if (featureOpt.isPresent()) {
      Feature feature = featureOpt.get();
      model.put("feature", feature);
      SortedSet<Comment> commentsWithoutDuplicates = getCommentsWithoutDuplicates(0, new HashSet<Long>(), feature.getComments());
      model.put("thread", commentsWithoutDuplicates);
      model.put("rootComment", new Comment());
    }
    // TODO: handle the situation where we can't find a feature by the featureId
    model.put("user", user);
    
    return "feature";
  }

  private SortedSet<Comment> getCommentsWithoutDuplicates(int page, Set<Long> visitedComments, SortedSet<Comment> comments) {
    page++;
    Iterator<Comment> itr = comments.iterator();
    while (itr.hasNext()) {
      Comment comment = itr.next();
      boolean addedToVisitedComments = visitedComments.add(comment.getId());
      if (!addedToVisitedComments) {
        itr.remove();
        if (page != 1)
          return comments;
      }
      if (addedToVisitedComments && !comment.getComments().isEmpty())
        getCommentsWithoutDuplicates(page, visitedComments, comment.getComments());
    }
    
    return comments;
  }
  
  @PostMapping("{featureId}")
  public String updateFeature (@AuthenticationPrincipal User user, Feature feature, @PathVariable Long productId, @PathVariable Long featureId) {
    feature.setUser(user);
    feature = featureService.save(feature);
    String encodedProductName;
    try {
      encodedProductName = URLEncoder.encode(feature.getProduct().getName(), "UTF-8");
    } catch (UnsupportedEncodingException e) {
      log.warn("Unable to encode the URL string: " + feature.getProduct().getName() + ", redirecting to dashboard instead of the intended product user view page.");
      return "redirect:/dashboard";
    }
    
    return "redirect:/p/"+encodedProductName;
  }
  
}
