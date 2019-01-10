package com.freshvotes.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
  
  @GetMapping("/products")
  public String getProducts(ModelMap model) {
    return "product";
  }
  
  @PostMapping("/products")
  public String createProduct() {
    
    return "redirect:/products";
  }
}
