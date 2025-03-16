package com.openclassrooms.chatop_api.services;

import com.openclassrooms.chatop_api.dto.RegisterDTO;
import com.openclassrooms.chatop_api.dto.UserDTO;
import com.openclassrooms.chatop_api.model.User;
import com.openclassrooms.chatop_api.dto.LoginDTO;
import com.openclassrooms.chatop_api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
  private final BCryptPasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final JWTService jwtService;

  public UserService(UserRepository userRepository, JWTService jwtService) {
    this.passwordEncoder = new BCryptPasswordEncoder();
    this.userRepository = userRepository;
    this.jwtService = jwtService;
  }

    public void registerUser(RegisterDTO registerDTO) {
    if (userRepository.findByEmail(registerDTO.getEmail()).isPresent()){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"email already used");
    }

    User newUser = new User(
      registerDTO.getName(),
      registerDTO.getEmail(),
      passwordEncoder.encode(registerDTO.getPassword())
    );

    userRepository.save(newUser);

  }

  public void logUser(LoginDTO user) {
    User foundUser = userRepository.findByEmail(user.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    if (!passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Password incorrect");
    }
  }

  public User findUserByEmail(String email) {
    return userRepository.findByEmail(email)
      .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  public UserDTO findUserById(Integer id) {
    User user = userRepository.findById(id).orElseThrow();
    return new UserDTO(user);
  }

  public Map<String, String> formatTokenResponse(Authentication authentication){

    String token = jwtService.generateToken(authentication);

    Map<String, String> response = new HashMap<>();
    response.put("token", token);

    return response;
  }
}
