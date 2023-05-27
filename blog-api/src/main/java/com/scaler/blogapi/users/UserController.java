package com.scaler.blogapi.users;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scaler.blogapi.users.dto.CreateUserDTO;
import com.scaler.blogapi.users.dto.LoginUserDTO;
import com.scaler.blogapi.users.dto.UserResponseDTO;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping("")
  public ResponseEntity<UserResponseDTO> createUser(@RequestBody CreateUserDTO createUserDTO) {
    UserResponseDTO savedUser = userService.createUser(createUserDTO);

    return ResponseEntity
    .created(URI.create("/users/" + savedUser.getId()))
    .body(savedUser);
    
  }

  @PostMapping("/login")
  public ResponseEntity<LoginUserDTO> loginuser(@RequestBody LoginUserDTO loginUserDTO) {
    return null;
  }
}
