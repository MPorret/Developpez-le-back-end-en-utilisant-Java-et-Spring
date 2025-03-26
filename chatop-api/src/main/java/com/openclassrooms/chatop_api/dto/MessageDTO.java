package com.openclassrooms.chatop_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MessageDTO {

  @Schema(description = "Message of the user about the rental", example = "Hi! I <would like to rent this beautiful house.")
  public String message;

  @Schema(description = "User's id", example = "4")
  @JsonProperty("user_id")
  public Integer userId;

  @Schema(description = "Rental's id", example = "102")
  @JsonProperty("rental_id")
  public Integer rentalId;
}
