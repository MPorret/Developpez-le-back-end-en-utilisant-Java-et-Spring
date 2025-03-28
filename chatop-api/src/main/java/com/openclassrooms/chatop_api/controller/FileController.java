package com.openclassrooms.chatop_api.controller;

import com.openclassrooms.chatop_api.dto.ImageDTO;
import com.openclassrooms.chatop_api.services.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Tag(name = "File's routes" )
public class FileController {
  private final FileService fileService;

  @GetMapping("/uploads/{imageName}")
  @Operation(summary = "Get an image")
  public ResponseEntity<Resource> getImage(@PathVariable String imageName) throws IOException {
    ImageDTO image = fileService.getImage(imageName);

    return ResponseEntity.ok()
      .contentType(MediaType.parseMediaType(image.contentType()))
      .body(image.resource());
  }
}
