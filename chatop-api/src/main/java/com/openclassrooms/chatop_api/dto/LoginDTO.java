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
  @Schema(description = "User's email", example = "john@doe.com")
  @JsonProperty("email")
  private String email;

  @Schema(description = "User's password", example = "J0hnDoe!")
  @JsonProperty("password")
  private String password;
}
