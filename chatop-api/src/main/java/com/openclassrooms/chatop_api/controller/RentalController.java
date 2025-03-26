package com.openclassrooms.chatop_api.controller;

import com.openclassrooms.chatop_api.dto.RentalDTO;
import com.openclassrooms.chatop_api.dto.RentalsDTO;
import com.openclassrooms.chatop_api.dto.ResponseDTO;
import com.openclassrooms.chatop_api.model.Rental;
import com.openclassrooms.chatop_api.services.RentalService;
import com.openclassrooms.chatop_api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/rentals")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@Tag(name = "Rental's routes")
public class RentalController {
  private final RentalService rentalService;
  private final UserService userService;

  @GetMapping
  @Operation(summary="Get all the rentals")
  public RentalsDTO getAllRentals(){
    List<RentalDTO> rentals = rentalService.getAllRentals()
      .stream().map(RentalDTO::new)
      .toList();
    return new RentalsDTO(rentals);
  }

  @GetMapping("/{id}")
  @Operation(summary="Get a specific rental based on id")
  public RentalDTO getRentalById(@PathVariable Integer id) {
    return new RentalDTO(rentalService.getRentalById(id));
  }

  @PostMapping(consumes = {"multipart/form-data"})
  @Operation(summary= "Create a new rental")
  public ResponseDTO createRental(
    @RequestPart("picture") MultipartFile picture,
    @RequestParam("name") String name,
    @RequestParam("description") String description,
    @RequestParam("price") Integer price,
    @RequestParam("surface") Integer surface,
    Authentication authentication
  ) throws IOException {

    Integer ownerId = userService.findUserByEmail(authentication.getName()).getId();

    rentalService.saveNewRental(name, description, price, surface, picture, ownerId);

    return new ResponseDTO("Rental created");
  }

  @PutMapping("/{id}")
  @Operation(summary = "Modify a rental")
  public ResponseDTO updateRental(
    @PathVariable Integer id,
    @RequestParam("name") String name,
    @RequestParam("description") String description,
    @RequestParam("price") Integer price,
    @RequestParam("surface") Integer surface){
    Rental rental = rentalService.getRentalById(id);
    rentalService.updateRental(rental, name, description, price, surface);
    return new ResponseDTO("Rental updated");
  }
}
