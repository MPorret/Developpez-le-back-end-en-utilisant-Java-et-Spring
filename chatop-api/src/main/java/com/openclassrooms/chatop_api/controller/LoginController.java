package com.openclassrooms.chatop_api.controller;

import com.openclassrooms.chatop_api.model.AuthResponse;
import com.openclassrooms.chatop_api.model.DBUser;
import com.openclassrooms.chatop_api.services.JWTService;
import com.openclassrooms.chatop_api.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  private final JWTService jwtService;
  private final UserService userService;
  private final AuthenticationManager authenticationManager;

  public LoginController(
    UserService userService,
    JWTService jwtService,
    AuthenticationManager authenticationManager
  ) {
    this.userService = userService;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
  }

  @PostMapping("/api/auth/register")
  public AuthResponse register(@RequestBody DBUser user) {

      userService.registerUser(user);

      Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
      );

      SecurityContextHolder.getContext().setAuthentication(authentication);

      String token = jwtService.generateToken(authentication);
      return new AuthResponse(token);
  }

  @PostMapping("/login")
  public String getToken(Authentication authentication) {
    return jwtService.generateToken(authentication);
  }
}
