package com.openclassrooms.chatop_api.controller;

import com.openclassrooms.chatop_api.dto.RegisterDTO;
import com.openclassrooms.chatop_api.dto.LoginDTO;
import com.openclassrooms.chatop_api.dto.TokenDTO;
import com.openclassrooms.chatop_api.dto.UserDTO;
import com.openclassrooms.chatop_api.model.User;
import com.openclassrooms.chatop_api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "User's routes")
public class UserController {

  private final UserService userService;

  public UserController(
    UserService userService
  ) {
    this.userService = userService;
  }

  @PostMapping("/api/auth/register")
  @Operation(summary= "Register an user")
  public TokenDTO register(@RequestBody RegisterDTO registerDTO) {

      String response = userService.registerUser(registerDTO);

      return new TokenDTO(response);
  }

  @PostMapping("/api/auth/login")
  @Operation(summary= "Log an user")
  public TokenDTO login(@RequestBody LoginDTO loginDTO) {

    String response = userService.logUser(loginDTO);

    return new TokenDTO(response);
  }

  @GetMapping("/api/auth/me")
  @Operation(summary = "Get information of connected user")
  public UserDTO getLoggedUser(Authentication authentication){
    User loggedUser = userService.findUserByEmail(authentication.getName());
    return new UserDTO(loggedUser);
  }

  @GetMapping("/api/user/{id}")
  @Operation(summary = "Get information about a specific user")
  public UserDTO getUser (@PathVariable Integer id){
    return new UserDTO(userService.findUserById(id));
  }
}
