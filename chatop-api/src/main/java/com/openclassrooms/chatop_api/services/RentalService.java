package com.openclassrooms.chatop_api.services;

import com.openclassrooms.chatop_api.dto.RentalDTO;
import com.openclassrooms.chatop_api.model.Rental;
import com.openclassrooms.chatop_api.repository.RentalRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class RentalService {
  private static final String UPLOAD_DIR = "uploads/";
  private final RentalRepository rentalRepository;

  public RentalService (RentalRepository rentalRepository) {
    this.rentalRepository = rentalRepository;
  }

  public void saveNewRental(RentalDTO rentalDTO, MultipartFile picture, Integer ownerId) throws IOException {
    // Save the picture
    String filePath = null;
    if (!picture.isEmpty()) {
      Path destinationPath = Path.of(UPLOAD_DIR, picture.getOriginalFilename());
      Files.copy(picture.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
      filePath = destinationPath.toString();
    }

    Rental newRental = new Rental(
      rentalDTO.getName(),
      rentalDTO.getSurface(),
      rentalDTO.getPrice(),
      filePath,
      rentalDTO.getDescription(),
      ownerId
    );

    rentalRepository.save(newRental);
  }
}
