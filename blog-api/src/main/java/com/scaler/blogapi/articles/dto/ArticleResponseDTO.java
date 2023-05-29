package com.scaler.blogapi.articles.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArticleResponseDTO {
  String title;
  String slug;
  String subTitle;
  String body;
  Date createdAt;
}
