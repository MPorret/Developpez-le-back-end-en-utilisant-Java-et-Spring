package com.openclassrooms.chatop_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LoginDTO {
  @Schema(description = "Identifiant de l'utilisateur", example = "john@doe.com")
  @JsonProperty("login")
  private String email;

  @Schema(description = "Mot de passe de l'utilisateur", example = "J0hnDoe!")
  @JsonProperty("password")
  private String password;
}
