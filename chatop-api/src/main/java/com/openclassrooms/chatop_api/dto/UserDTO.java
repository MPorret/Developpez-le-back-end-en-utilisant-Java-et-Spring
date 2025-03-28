package com.openclassrooms.chatop_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.openclassrooms.chatop_api.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDTO {

  @Schema(description = "User's id", example = "4")
  private Integer id;

  @Schema(description = "User's name", example = "John Doe")
  private String name;

  @Schema(description = "User's email", example = "john@doe.com")
  private String email;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("updated_at")
  private Date updatedAt;

  public UserDTO (User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.email = user.getEmail();
    this.createdAt = user.getCreatedAt();
    this.updatedAt = user.getUpdatedAt();
  }
}
