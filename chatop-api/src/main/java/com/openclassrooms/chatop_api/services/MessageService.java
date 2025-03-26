package com.openclassrooms.chatop_api.services;

import com.openclassrooms.chatop_api.dto.MessageDTO;
import com.openclassrooms.chatop_api.model.Message;
import com.openclassrooms.chatop_api.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
  private final MessageRepository messageRepository;
  private final UserService userService;
  private final RentalService rentalService;

  public void saveMessage(MessageDTO message){
    Message newMessage = new Message(
      message.getMessage(),
      userService.findUserById(message.getUserId()),
      rentalService.getRentalById(message.getRentalId())
      );
    messageRepository.save(newMessage);
  }
}
