package com.openclassrooms.chatop_api.model;

import com.openclassrooms.chatop_api.dto.MessageDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "`MESSAGES`")
public class Message {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(nullable = false)
  @Size(max=2000, message = "The message cannot exceed 2000 characters")
  private String message;

  @Column(nullable = false, name = "user_id")
  private Integer userId;

  @Column(nullable = false, name = "rental_id")
  private Integer rentalId;

  @CreationTimestamp
  @Column(updatable = false, name = "created_at")
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Date updatedAt;

  public Message(MessageDTO messageDTO) {
    this.message = messageDTO.getMessage();
    this.userId = messageDTO.getUserId();
    this.rentalId = messageDTO.getRentalId();
  }
}
