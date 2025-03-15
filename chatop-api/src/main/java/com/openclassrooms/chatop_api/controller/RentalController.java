package com.openclassrooms.chatop_api.controller;

import com.openclassrooms.chatop_api.dto.MessageResponse;
import com.openclassrooms.chatop_api.dto.RentalDTO;
import com.openclassrooms.chatop_api.services.RentalService;
import com.openclassrooms.chatop_api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Tag(name = "Rental controller")
public class RentalController {
  private final RentalService rentalService;
  private final UserService userService;

  public RentalController(RentalService rentalService, UserService userService){
    this.rentalService = rentalService;
    this.userService = userService;
  }

  @PostMapping(value = "/api/rentals", consumes = {"multipart/form-data"})
  @Operation(
    summary= "Create a new rental"
  )
  public MessageResponse createRental(
    @RequestPart("picture") MultipartFile picture,
    @RequestParam("name") String name,
    @RequestParam("description") String description,
    @RequestParam("price") Integer price,
    @RequestParam("surface") Integer surface,
    Authentication authentication
  ) throws IOException {

    RentalDTO newRentalDTO = new RentalDTO(name, surface, price, description);
    Integer ownerId = userService.foundOwnerId(authentication.getName());

    rentalService.saveNewRental(newRentalDTO, picture, ownerId);

    String message = "Rental created";
    return new MessageResponse(message);
  }
}
