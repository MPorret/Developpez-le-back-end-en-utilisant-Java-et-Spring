package com.openclassrooms.chatop_api.services;

import com.openclassrooms.chatop_api.model.DBUser;
import com.openclassrooms.chatop_api.repository.DBUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
  private final BCryptPasswordEncoder passwordEncoder;
  private final DBUserRepository userRepository;

  public UserService(DBUserRepository userRepository) {
    this.passwordEncoder = new BCryptPasswordEncoder();
    this.userRepository = userRepository;
  }

  public void registerUser(DBUser user) {
    if (userRepository.findByEmail(user.getEmail()).isPresent()){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"email already used");
    }

    DBUser newUser = new DBUser(
      user.getName(),
      user.getEmail(),
      passwordEncoder.encode(user.getPassword()) // Chiffrement du mot de passe
    );

    userRepository.save(newUser);

  }
}
