package com.example.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Z6PWA
 * Date: 28.10.2023
 */
@RestController
public class Controller
{
  @GetMapping("/basic")
  public String basicAccess()
  {
    return "This is page with basic access permission.";
  }

  @GetMapping("/advanced")
  public String advancedAccess()
  {
    return "This is page with advanced access permission.";
  }

  @GetMapping("/admin")
  public String adminAccess()
  {
    return "This is page with admin access permission.";
  }
}
