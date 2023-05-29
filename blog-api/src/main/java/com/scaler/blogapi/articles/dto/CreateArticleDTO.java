package com.scaler.blogapi.articles.dto;

import com.scaler.blogapi.users.UserEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CreateArticleDTO {
  String title;
  String slug;
  String subTitle;
  String body;
  UserEntity author;
}
