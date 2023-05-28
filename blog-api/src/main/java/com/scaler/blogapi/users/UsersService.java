package com.scaler.blogapi.users;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.scaler.blogapi.users.dto.CreateUserDTO;
import com.scaler.blogapi.users.dto.LoginUserDTO;
import com.scaler.blogapi.users.dto.UserResponseDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsersService {
  private final UsersRepository userRepository;
  private final ModelMapper modelMapper;

  public UserResponseDTO createUser(CreateUserDTO createUserDTO) {
    // TODO: Encrypt password
    // TODO: Validate email
    // TODO: Check if username already exists
    UserEntity newUserEntity = modelMapper.map(createUserDTO, UserEntity.class);

    UserEntity savedUser = userRepository.save(newUserEntity);

    UserResponseDTO userResponseDTO = modelMapper.map(savedUser, UserResponseDTO.class);

    return userResponseDTO;
  }

  public UserResponseDTO loginUser(LoginUserDTO loginUserDTO) {
    List<UserEntity> userEntity = userRepository.findByUsername(loginUserDTO.getUsername());

    if(userEntity.isEmpty()) throw new UserNotFoundException(loginUserDTO.getUsername());

    // TODO: Decrypt password
    if(!userEntity.get(0).getPassword().equals(loginUserDTO.getPassword())) throw new IncorrectPasswordException();

    UserResponseDTO userResponseDTO = modelMapper.map(userEntity.get(0), UserResponseDTO.class);

    return userResponseDTO;  
  }

  public static class UserNotFoundException extends IllegalArgumentException {
    public UserNotFoundException(Integer id) {
      super("User with id: " + id + " not found!");
    }

    public UserNotFoundException(String username) {
      super("User with username: " + username + " not found!");
    }
  }
  
  public static class IncorrectPasswordException extends IllegalArgumentException {
    public IncorrectPasswordException() {
      super("Incorrect password!");
    }
  }

  public UserResponseDTO getUser(Integer id) {
    UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

    return modelMapper.map(userEntity, UserResponseDTO.class);
  }

  public List<UserResponseDTO> getProfiles() {
    List<UserEntity> listOfUserEntities = userRepository.findAll();
    List<UserResponseDTO> listOfUserResponseDTOs = new ArrayList<>();

    for(UserEntity user : listOfUserEntities) {
      listOfUserResponseDTOs.add(modelMapper.map(user, UserResponseDTO.class));
    }

    return listOfUserResponseDTOs;
  }
}
