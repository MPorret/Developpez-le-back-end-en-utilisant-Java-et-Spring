package com.openclassrooms.chatop_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@Tag(name = "File's routes" )
public class FileController {
  private final String UPLOAD_DIR = "uploads/"; // Dossier où sont stockées les images

  @GetMapping("/uploads/{imageName}")
  @Operation(summary = "Get an image")
  public ResponseEntity<Resource> getImage(@PathVariable String imageName) throws IOException {
    Path imagePath = Paths.get(UPLOAD_DIR).resolve(imageName).normalize();
    Resource resource = new UrlResource(imagePath.toUri());

    if (!resource.exists() || !resource.isReadable()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
    }

    // Détecte le type MIME du fichier (image/jpeg, image/png, etc.)
    String contentType = Files.probeContentType(imagePath);
    if (contentType == null) {
      contentType = "application/octet-stream";
    }

    return ResponseEntity.ok()
      .contentType(MediaType.parseMediaType(contentType))
      .body(resource);
  }
}
