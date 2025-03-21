package com.openclassrooms.chatop_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.openclassrooms.chatop_api.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LoginDTO {
  @Schema(description = "Identifiant de l'utilisateur", example = "john@doe.com")
  @JsonProperty("email")
  private String email;

  @Schema(description = "Mot de passe de l'utilisateur", example = "J0hnDoe!")
  @JsonProperty("password")
  private String password;

  public LoginDTO(User user) {
    this.email = user.getEmail();
    this.password = user.getPassword();
  }
}
