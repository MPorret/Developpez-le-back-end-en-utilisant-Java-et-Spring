package com.openclassrooms.chatop_api.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class RentalsDTO {
  public List<RentalDTO> rentals;

  public RentalsDTO (List<RentalDTO> rentals){
    this.rentals = rentals;
  }
}
