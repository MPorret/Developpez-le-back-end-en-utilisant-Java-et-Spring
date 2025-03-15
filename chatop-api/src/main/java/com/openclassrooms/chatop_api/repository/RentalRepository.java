package com.openclassrooms.chatop_api.repository;

import com.openclassrooms.chatop_api.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
  Optional<Rental> findById(Integer id);
}
