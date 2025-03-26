package com.openclassrooms.chatop_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MessageDTO {
  public String message;
  @JsonProperty("user_id")
  public Integer userId;
  @JsonProperty("rental_id")
  public Integer rentalId;
}
