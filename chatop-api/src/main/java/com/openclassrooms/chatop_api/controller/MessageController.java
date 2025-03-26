package com.openclassrooms.chatop_api.controller;

import com.openclassrooms.chatop_api.dto.MessageDTO;
import com.openclassrooms.chatop_api.dto.ResponseDTO;
import com.openclassrooms.chatop_api.services.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@Tag(name = "Message's route")
public class MessageController {
  private final MessageService messageService;

  @PostMapping
  @Operation(summary="Send a message")
  public ResponseDTO sendMessage(
    @RequestBody MessageDTO messageDTO) {
    messageService.saveMessage(messageDTO);
    return new ResponseDTO("Message send with success");
  }
}
