package com.openclassrooms.chatop_api.services;

import com.openclassrooms.chatop_api.dto.ImageDTO;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
public class FileService {
  private static final String UPLOAD_DIR = "uploads/";

  public String saveFile (MultipartFile file) throws IOException {
    // Path initialization
    String filePath = null;
    if (!file.isEmpty()) {
      // Create the Path of the image
      Path destinationPath = Path.of(UPLOAD_DIR, file.getOriginalFilename());
      // Put the image in the destination path
      Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
      // Convert path to string
      filePath = destinationPath.toString();
    }

    return filePath;
  }

  public ImageDTO getImage(String imageName) throws IOException {
    String UPLOAD_DIR = "uploads/";

    // Resolve the file path for the given image name
    Path imagePath = Paths.get(UPLOAD_DIR).resolve(imageName).normalize();

    // Create a Resource object (UrlResource) based on the image path
    Resource resource = new UrlResource(imagePath.toUri());

    // Check if the image exists and if it is readable (accessible)
    if (!resource.exists() || !resource.isReadable()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
    }

    // Get the content type of the files
    String contentType = Files.probeContentType(imagePath);

    // If not, set a default content type
    if (contentType == null) {
      contentType = "application/octet-stream";
    }

    return new ImageDTO(contentType, resource);
  }
}
