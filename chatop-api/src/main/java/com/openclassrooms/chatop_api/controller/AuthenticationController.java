package com.openclassrooms.chatop_api.controller;

import com.openclassrooms.chatop_api.dto.RegisterDTO;
import com.openclassrooms.chatop_api.dto.AuthResponse;
import com.openclassrooms.chatop_api.dto.LoginDTO;
import com.openclassrooms.chatop_api.services.JWTService;
import com.openclassrooms.chatop_api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Authentification")
public class AuthenticationController {

  private final JWTService jwtService;
  private final UserService userService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationController(
    UserService userService,
    JWTService jwtService,
    AuthenticationManager authenticationManager
  ) {
    this.userService = userService;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
  }

  @PostMapping("/api/auth/register")
  @Operation(
    summary= "Inscrire un utilisateur"
  )
  public AuthResponse register(@RequestBody RegisterDTO registerDTO) {

      userService.registerUser(registerDTO);

      Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(registerDTO.getEmail(), registerDTO.getPassword())
      );

      SecurityContextHolder.getContext().setAuthentication(authentication);

      String token = jwtService.generateToken(authentication);
      return new AuthResponse(token);
  }

  @PostMapping("/api/auth/login")
  @Operation(
    summary= "Identifier un utilisateur"
  )
  public AuthResponse login(@RequestBody LoginDTO loginDTO) {

    userService.logUser(loginDTO);

    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = jwtService.generateToken(authentication);
    return new AuthResponse(token);
  }
}
