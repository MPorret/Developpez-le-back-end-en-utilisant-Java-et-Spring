package com.openclassrooms.chatop_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.openclassrooms.chatop_api.model.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDTO {
  private Integer id;
  private String name;
  private String email;

  @JsonProperty("created_at")
  private String createdAt;

  @JsonProperty("updated_at")
  private String updatedAt;

  public UserDTO (User user) {
    SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");

    this.id = user.getId();
    this.name = user.getName();
    this.email = user.getEmail();
    this.createdAt = formatDate.format(user.getCreatedAt());
    this.updatedAt = formatDate.format(user.getUpdatedAt());
  }
}
