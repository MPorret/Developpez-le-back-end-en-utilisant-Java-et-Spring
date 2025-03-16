package com.openclassrooms.chatop_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@RequiredArgsConstructor
public class RentalDTO {
  @Schema(description = "Name of the rental", example = "Colored house")
  private String name;

  @Schema(description = "Surface of the rental", example = "35")
  private Integer surface;

  @Schema(description = "Price of the rental", example = "153")
  private Integer price;

  @Schema(description = "Description of the rental", example = "Amazing house with a view")
  private String description;

  public RentalDTO(String name, Integer surface, Integer price, String description) {
    this.name = name;
    this.surface = surface;
    this.price = price;
    this.description = description;
  }
}
