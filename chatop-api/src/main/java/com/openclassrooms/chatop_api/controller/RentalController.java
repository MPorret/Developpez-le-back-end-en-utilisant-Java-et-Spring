package com.openclassrooms.chatop_api.controller;

import com.openclassrooms.chatop_api.dto.RentalDTO;
import com.openclassrooms.chatop_api.model.Rental;
import com.openclassrooms.chatop_api.services.RentalService;
import com.openclassrooms.chatop_api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "Rental's routes")
public class RentalController {
  private final RentalService rentalService;
  private final UserService userService;

  public RentalController(RentalService rentalService, UserService userService){
    this.rentalService = rentalService;
    this.userService = userService;
  }

  @GetMapping("/api/rentals")
  @Operation(summary="Get all the rentals")
  public ResponseEntity<Map<String, List<RentalDTO>>> getAllRentals(){
    Map<String, List<RentalDTO>> response = rentalService.getAllRentals();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/api/rentals/{id}")
  @Operation(summary="Get a specific rental based on id")
  public RentalDTO getRentalById(@PathVariable Long id) {
    return rentalService.getRentalById(id);
  }

  @PostMapping(value = "/api/rentals", consumes = {"multipart/form-data"})
  @Operation(summary= "Create a new rental")
  public ResponseEntity<Map<String, String>> createRental(
    @RequestPart("picture") MultipartFile picture,
    @RequestParam("name") String name,
    @RequestParam("description") String description,
    @RequestParam("price") Integer price,
    @RequestParam("surface") Integer surface,
    Authentication authentication
  ) throws IOException {

    Integer ownerId = userService.findUserByEmail(authentication.getName()).getId();

    Map<String, String> response = rentalService.saveNewRental(name, description, price, surface, picture, ownerId);

    return ResponseEntity.ok(response);
  }
}
