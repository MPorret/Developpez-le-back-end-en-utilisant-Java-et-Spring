package com.openclassrooms.chatop_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.openclassrooms.chatop_api.model.Rental;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class RentalDTO {
  @Schema(description = "Id of the rental", example= "35")
  private Integer id;

  @Schema(description = "Name of the rental", example = "Colored house")
  private String name;

  @Schema(description = "Surface of the rental", example = "35")
  private Integer surface;

  @Schema(description = "Price of the rental", example = "153")
  private Integer price;

  @Schema(description = "Picture of the rental", example = "['https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg\']")
  private List<String> picture;

  @Schema(description = "Description of the rental", example = "Amazing house with a view")
  private String description;

  @JsonProperty("created_at")
  private String createdAt;

  @JsonProperty("updated_at")
  private String updatedAt;

  @Schema(description = "Owner id of the rental", example = "2")
  private Integer ownerId;

  public RentalDTO(Rental rental) {
    SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd");
    List<String> pictureList = new ArrayList<String>();
    pictureList.add("http://localhost:3001/" + rental.getPicture());

    this.id = rental.getId();
    this.name = rental.getName();
    this.surface = rental.getSurface();
    this.price = rental.getPrice();
    this.picture = pictureList;
    this.description = rental.getDescription();
    this.createdAt = formatDate.format(rental.getCreatedAt());
    this.updatedAt = formatDate.format(rental.getUpdatedAt());
    this.ownerId = rental.getOwnerId();
  }
}
