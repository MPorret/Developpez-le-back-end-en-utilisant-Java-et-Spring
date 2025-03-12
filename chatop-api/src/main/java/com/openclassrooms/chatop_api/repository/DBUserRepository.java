package com.openclassrooms.chatop_api.repository;

import com.openclassrooms.chatop_api.model.DBUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DBUserRepository extends JpaRepository<DBUser, Integer> {
  public Optional<DBUser> findByEmail(String email);
}
