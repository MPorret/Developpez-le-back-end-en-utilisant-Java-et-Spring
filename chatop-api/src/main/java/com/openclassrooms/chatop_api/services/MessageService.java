package com.openclassrooms.chatop_api.services;

import com.openclassrooms.chatop_api.dto.MessageDTO;
import com.openclassrooms.chatop_api.model.Message;
import com.openclassrooms.chatop_api.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
  private MessageRepository messageRepository;

  public MessageService(MessageRepository messageRepository){
    this.messageRepository = messageRepository;
  }

  public void saveMessage(MessageDTO message){
    Message newMessage = new Message(message);
    messageRepository.save(newMessage);
  }
}
