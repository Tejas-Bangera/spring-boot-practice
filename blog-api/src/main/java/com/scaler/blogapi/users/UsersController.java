package com.scaler.blogapi.users;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scaler.blogapi.users.dto.CreateUserDTO;
import com.scaler.blogapi.users.dto.ErrorResponseDTO;
import com.scaler.blogapi.users.dto.LoginUserDTO;
import com.scaler.blogapi.users.dto.UserProfileResponseDTO;
import com.scaler.blogapi.users.dto.UserResponseDTO;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UsersController {
  private final UsersService usersService;

  @PostMapping("/users")
  public ResponseEntity<UserResponseDTO> createUser(@RequestBody CreateUserDTO createUserDTO) {
    UserResponseDTO savedUser = usersService.createUser(createUserDTO);

    return ResponseEntity
    .created(URI.create("/users/" + savedUser.getId()))
    .body(savedUser);
    
  }

  @PostMapping("/users/login")
  public ResponseEntity<UserResponseDTO> loginuser(@RequestBody LoginUserDTO loginUserDTO) {
    return ResponseEntity.ok(usersService.loginUser(loginUserDTO));
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<UserResponseDTO> getUser(@PathVariable Integer id) {
    return ResponseEntity.ok(usersService.getUser(id));
  }

  @GetMapping("/profiles")
  public ResponseEntity<List<UserProfileResponseDTO>> getProfiles() {
    return ResponseEntity.ok(usersService.getProfiles());
  }

  @GetMapping("/profiles/{userName}")
  public ResponseEntity<UserProfileResponseDTO> getProfileByUserName(@PathVariable String userName) {
    return ResponseEntity.ok(usersService.getProfileByUserName(userName));
  }

  @ExceptionHandler(UsersService.UserNotFoundException.class)
  public ResponseEntity<ErrorResponseDTO> handleUserNotFoundError(UsersService.UserNotFoundException errorMessage) {
    return new ResponseEntity<ErrorResponseDTO>(new ErrorResponseDTO(errorMessage.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UsersService.IncorrectPasswordException.class)
  public ResponseEntity<ErrorResponseDTO> handleIncorrectPasswordError(UsersService.IncorrectPasswordException errorMessage) {
    return new ResponseEntity<ErrorResponseDTO>(new ErrorResponseDTO(errorMessage.getMessage()), HttpStatus.UNAUTHORIZED);
  }
}
