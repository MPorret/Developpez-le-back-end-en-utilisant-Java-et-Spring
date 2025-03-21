package com.openclassrooms.chatop_api.services;

import com.openclassrooms.chatop_api.dto.RegisterDTO;
import com.openclassrooms.chatop_api.dto.UserDTO;
import com.openclassrooms.chatop_api.model.User;
import com.openclassrooms.chatop_api.dto.LoginDTO;
import com.openclassrooms.chatop_api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
  private final BCryptPasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final JWTService jwtService;
  private final AuthenticationManager authenticationManager;

  public UserService(UserRepository userRepository, JWTService jwtService, AuthenticationManager authenticationManager) {
    this.passwordEncoder = new BCryptPasswordEncoder();
    this.userRepository = userRepository;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
  }

    public String registerUser(RegisterDTO registerDTO) {
    userRepository.findByEmail(registerDTO.getEmail())
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"email already used"));

    User newUser = new User(
      registerDTO.getName(),
      registerDTO.getEmail(),
      passwordEncoder.encode(registerDTO.getPassword())
    );

    userRepository.save(newUser);

    return authUser(
      registerDTO.getName(),
      registerDTO.getEmail()
    );
  }

  public String logUser(LoginDTO user) {
    User foundUser = userRepository.findByEmail(user.getEmail())
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    if (!passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Password incorrect");
    }

    return authUser(user.getEmail(), user.getPassword());
  }

  public String authUser(String email, String password) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(email, password)
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);

    return formatTokenResponse(authentication);
  }

  public User findUserByEmail(String email) {
    return userRepository.findByEmail(email)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
  }

  public User findUserById(Integer id) {
    return userRepository.findById(id)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
  }

  public String formatTokenResponse(Authentication authentication){
    return jwtService.generateToken(authentication);
  }
}
