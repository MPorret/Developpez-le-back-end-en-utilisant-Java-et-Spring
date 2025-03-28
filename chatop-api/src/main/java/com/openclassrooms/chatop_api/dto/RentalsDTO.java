package com.openclassrooms.chatop_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record RentalsDTO(@Schema(description = "List of rentals") List<RentalDTO> rentals) {
}
