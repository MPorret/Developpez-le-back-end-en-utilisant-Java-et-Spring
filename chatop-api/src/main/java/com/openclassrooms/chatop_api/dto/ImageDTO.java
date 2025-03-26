package com.openclassrooms.chatop_api.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;

@Getter
@RequiredArgsConstructor
public class ImageDTO {
  private String contentType;
  private Resource resource;

  public ImageDTO(String contentType, Resource resource){
    this.contentType = contentType;
    this.resource = resource;
  }
}
