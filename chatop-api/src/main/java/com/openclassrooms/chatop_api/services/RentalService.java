package com.openclassrooms.chatop_api.services;

import com.openclassrooms.chatop_api.dto.RentalDTO;
import com.openclassrooms.chatop_api.model.Rental;
import com.openclassrooms.chatop_api.repository.RentalRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class RentalService {
  private final RentalRepository rentalRepository;
  private final FileService fileService;

  public RentalService (RentalRepository rentalRepository, FileService fileService) {
    this.rentalRepository = rentalRepository;
    this.fileService = fileService;
  }

  public Map<String, List<RentalDTO>> getAllRentals(){
    Map<String, List<RentalDTO>> response = new HashMap<>();
    response.put("rentals",
      rentalRepository.findAll()
        .stream().map(RentalDTO::new)
        .collect(Collectors.toList()));
    return response;
  }

  public Map<String, String> saveNewRental(
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
      ownerId
    );

    rentalRepository.save(newRental);

    Map<String, String> response = new HashMap<>();
    response.put("message", "Rental created");

    return response;
  }

  public Rental getRentalById(Long id) {
    return rentalRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Rental not found"));
  }

  public Map<String, String> updateRental(Rental rental, String name, String description, Integer price, Integer surface) {
    rental.setName(name);
    rental.setPrice(price);
    rental.setDescription(description);
    rental.setSurface(surface);
    rentalRepository.save(rental);

    Map<String, String> response = new HashMap<>();
    response.put("message", "Rental updated");

    return response;
  }
}
