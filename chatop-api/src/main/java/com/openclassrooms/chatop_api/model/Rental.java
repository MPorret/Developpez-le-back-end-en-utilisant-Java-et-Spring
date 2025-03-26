package com.openclassrooms.chatop_api.model;

import com.openclassrooms.chatop_api.dto.RentalDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name="`RENTALS`")
public class Rental {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(nullable = false)
  @Size(max=255, message = "The rental's name cannot exceed 255 characters")
  private String name;

  @Column(nullable = false)
  private Integer surface;

  @Column(nullable = false)
  private Integer price;

  @Size(max=255, message = "The picture's name cannot exceed 255 characters")
  private String picture;

  @Column(nullable = false)
  @Size(max=2000, message = "The description cannot exceed 2000 characters")
  private String description;

  @Column(nullable = false, name = "owner_id")
  private Integer ownerId;

  @CreationTimestamp
  @Column(updatable = false, name = "created_at")
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Date updatedAt;

  public Rental(){}

  public Rental(String name, Integer surface, Integer price, String picture, String description, Integer ownerId){
    this.name = name;
    this.surface = surface;
    this.price = price;
    this.picture = picture;
    this.description = description;
    this.ownerId = ownerId;
  }
}
