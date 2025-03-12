package com.openclassrooms.chatop_api.configuration;

import com.openclassrooms.chatop_api.model.DBUser;
import com.openclassrooms.chatop_api.repository.DBUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  private final DBUserRepository dbUserRepository;

  public CustomUserDetailsService (DBUserRepository dbUserRepository){
    this.dbUserRepository = dbUserRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    DBUser user = dbUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

    return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
  }
}
