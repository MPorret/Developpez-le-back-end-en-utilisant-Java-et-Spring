package com.openclassrooms.chatop_api.controller;

import com.openclassrooms.chatop_api.dto.MessageDTO;
import com.openclassrooms.chatop_api.dto.ResponseDTO;
import com.openclassrooms.chatop_api.services.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Message's route")
public class MessageController {
  private final MessageService messageService;

  public MessageController (MessageService messageService){
    this.messageService = messageService;
  }

  @PostMapping("/api/messages")
  @Operation(summary="Send a message")
  public ResponseDTO sendMessage(
    @RequestBody MessageDTO messageDTO) {
    messageService.saveMessage(messageDTO);
    return new ResponseDTO("Message send with success");
  }
}
