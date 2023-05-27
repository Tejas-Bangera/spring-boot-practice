package com.scaler.blogapi.users;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.scaler.blogapi.users.dto.CreateUserDTO;

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
}
