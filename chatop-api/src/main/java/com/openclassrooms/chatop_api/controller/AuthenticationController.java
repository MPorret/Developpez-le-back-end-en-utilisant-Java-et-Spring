package com.openclassrooms.chatop_api.controller;

import com.openclassrooms.chatop_api.dto.RegisterDTO;
import com.openclassrooms.chatop_api.dto.LoginDTO;
import com.openclassrooms.chatop_api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Tag(name = "Authentification")
public class AuthenticationController {

  private final UserService userService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationController(
    UserService userService,
    AuthenticationManager authenticationManager
  ) {
    this.userService = userService;
    this.authenticationManager = authenticationManager;
  }

  @PostMapping("/api/auth/register")
  @Operation(
    summary= "Register an user"
  )
  public ResponseEntity<Map<String, String>> register(@RequestBody RegisterDTO registerDTO) {

      userService.registerUser(registerDTO);

      Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(registerDTO.getEmail(), registerDTO.getPassword())
      );
      SecurityContextHolder.getContext().setAuthentication(authentication);

      Map<String, String> tokenResponse = userService.formatTokenResponse(authentication);

      return ResponseEntity.ok(tokenResponse);
  }

  @PostMapping("/api/auth/login")
  @Operation(
    summary= "Log an user"
  )
  public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO) {

    userService.logUser(loginDTO);

    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);

    Map<String, String> tokenResponse = userService.formatTokenResponse(authentication);

    return ResponseEntity.ok(tokenResponse);
  }
}
