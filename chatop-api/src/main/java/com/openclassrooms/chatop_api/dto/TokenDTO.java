package com.openclassrooms.chatop_api.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TokenDTO {
  private String token;

  public TokenDTO(String token) {
    this.token = token;
  }
}
