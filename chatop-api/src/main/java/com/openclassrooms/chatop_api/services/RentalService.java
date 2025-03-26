package com.openclassrooms.chatop_api.services;

import com.openclassrooms.chatop_api.model.Rental;
import com.openclassrooms.chatop_api.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {
  private final RentalRepository rentalRepository;
  private final FileService fileService;
  private final UserService userService;

  public List<Rental> getAllRentals(){
   return rentalRepository.findAll();
  }

  public void saveNewRental(
    String name,
    String description,
    Integer price,
    Integer surface,
    MultipartFile picture,
    Integer ownerId
  ) throws IOException {

    String filePath = fileService.saveFile(picture);

    Rental newRental = new Rental(
      name,
      surface,
      price,
      filePath,
      description,
      userService.findUserById(ownerId)
    );

    rentalRepository.save(newRental);
  }

  public Rental getRentalById(Integer id) {
    return rentalRepository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Rental not found"));
  }

  public void updateRental(Rental rental, String name, String description, Integer price, Integer surface) {
    rental.setName(name);
    rental.setPrice(price);
    rental.setDescription(description);
    rental.setSurface(surface);

    rentalRepository.save(rental);
  }
}
