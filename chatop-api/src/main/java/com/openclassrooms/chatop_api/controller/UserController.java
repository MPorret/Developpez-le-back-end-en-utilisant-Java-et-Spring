package com.openclassrooms.chatop_api.controller;

import com.openclassrooms.chatop_api.dto.RegisterDTO;
import com.openclassrooms.chatop_api.dto.LoginDTO;
import com.openclassrooms.chatop_api.dto.TokenDTO;
import com.openclassrooms.chatop_api.dto.UserDTO;
import com.openclassrooms.chatop_api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "User's routes")
public class UserController {

  private final UserService userService;

  @PostMapping("/auth/register")
  @Operation(summary= "Register an user")
  public TokenDTO register(@RequestBody RegisterDTO registerDTO) {
      return new TokenDTO(userService.registerUser(registerDTO));
  }

  @PostMapping("/auth/login")
  @Operation(summary= "Log an user")
  public TokenDTO login(@RequestBody LoginDTO loginDTO) {
    return new TokenDTO(userService.logUser(loginDTO));
  }

  @SecurityRequirement(name = "bearerAuth")
  @GetMapping("/auth/me")
  @Operation(summary = "Get information of connected user")
  public UserDTO getLoggedUser(Authentication authentication){
    return new UserDTO(userService.findUserByEmail(authentication.getName()));
  }

  @SecurityRequirement(name = "bearerAuth")
  @GetMapping("/user/{id}")
  @Operation(summary = "Get information about a specific user")
  public UserDTO getUser (@PathVariable Integer id){
    return new UserDTO(userService.findUserById(id));
  }
}
