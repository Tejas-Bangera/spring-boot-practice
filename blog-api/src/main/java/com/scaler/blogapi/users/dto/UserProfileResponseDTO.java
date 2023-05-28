package com.scaler.blogapi.users.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserProfileResponseDTO {
  String username;
  String bio;
  String image;
}
