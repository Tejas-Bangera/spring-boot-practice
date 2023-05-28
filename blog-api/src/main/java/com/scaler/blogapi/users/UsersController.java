package com.scaler.blogapi.users;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scaler.blogapi.users.dto.CreateUserDTO;
import com.scaler.blogapi.users.dto.ErrorResponseDTO;
import com.scaler.blogapi.users.dto.LoginUserDTO;
import com.scaler.blogapi.users.dto.UserResponseDTO;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {
  private final UsersService usersService;

  @PostMapping("")
  public ResponseEntity<UserResponseDTO> createUser(@RequestBody CreateUserDTO createUserDTO) {
    UserResponseDTO savedUser = usersService.createUser(createUserDTO);

    return ResponseEntity
    .created(URI.create("/users/" + savedUser.getId()))
    .body(savedUser);
    
  }

  @PostMapping("/login")
  public ResponseEntity<UserResponseDTO> loginuser(@RequestBody LoginUserDTO loginUserDTO) {
    return ResponseEntity.ok(usersService.loginUser(loginUserDTO));
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDTO> getUser(@PathVariable Integer id) {
    return ResponseEntity.ok(usersService.getUser(id));
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
