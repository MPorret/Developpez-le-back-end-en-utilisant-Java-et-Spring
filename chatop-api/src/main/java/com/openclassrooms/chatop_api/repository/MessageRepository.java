package com.openclassrooms.chatop_api.repository;

import com.openclassrooms.chatop_api.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
  Optional<Message> findById(Long id);
}
