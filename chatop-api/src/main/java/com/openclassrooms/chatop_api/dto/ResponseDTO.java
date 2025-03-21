package com.openclassrooms.chatop_api.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResponseDTO {
  private String message;

  public ResponseDTO(String message) {
    this.message = message;
  }
}
