package com.scaler.blogapi.users;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.scaler.blogapi.users.UsersService.UserNotFoundException;
import com.scaler.blogapi.users.dto.CreateUserDTO;
import com.scaler.blogapi.users.dto.LoginUserDTO;

@DataJpaTest
public class UsersServiceTests {
  @Autowired private UsersRepository usersRepository;
  private UsersService usersService;

  // Singleton
  private UsersService createUsersService() {
    if(usersService == null) {
      usersService = new UsersService(usersRepository, new ModelMapper());
    }
    return usersService;
  }

  @Test
  public void testCreateUser() {
    UsersService usersService = createUsersService();
    CreateUserDTO newUserDTO = new CreateUserDTO();
    newUserDTO.setUsername("john.doe");
    newUserDTO.setPassword("john@123");
    newUserDTO.setEmail("john.doe@xyz.com");
    var savedUser = usersService.createUser(newUserDTO);

    assertNotNull(savedUser);
  }

  @Test
  public void testUserNotFound() {
    UsersService usersService = createUsersService();
    CreateUserDTO newUserDTO = new CreateUserDTO();
    newUserDTO.setUsername("john.doe");
    newUserDTO.setPassword("john@123");
    newUserDTO.setEmail("john.doe@xyz.com");
    usersService.createUser(newUserDTO);

    LoginUserDTO loginUserDTO = new LoginUserDTO();
    loginUserDTO.setUsername("john.boe");
    loginUserDTO.setPassword("john@123");
    loginUserDTO.setEmail("john.doe@xyz.com");

    assertThrows(UserNotFoundException.class, () -> usersService.loginUser(loginUserDTO));
  }
}
