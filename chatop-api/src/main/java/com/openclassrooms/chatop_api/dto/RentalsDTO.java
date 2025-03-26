package com.openclassrooms.chatop_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class RentalsDTO {
  @Schema(description = "List of rentals")
  public List<RentalDTO> rentals;

  public RentalsDTO (List<RentalDTO> rentals){
    this.rentals = rentals;
  }
}
