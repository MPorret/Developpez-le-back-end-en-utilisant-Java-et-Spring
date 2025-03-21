package com.openclassrooms.chatop_api.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {
  private static final String UPLOAD_DIR = "uploads/";

  public String saveFile (MultipartFile file) throws IOException {
    // Save the file
    String filePath = null;
    if (!file.isEmpty()) {
      Path destinationPath = Path.of(UPLOAD_DIR, file.getOriginalFilename());
      Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
      filePath = destinationPath.toString();
    }

    return filePath;
  }
}
