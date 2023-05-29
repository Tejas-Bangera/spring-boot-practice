package com.scaler.blogapi.articles;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.scaler.blogapi.articles.dto.CreateArticleDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArticlesService {
  private final ArticlesRepository articlesRepository;
  private final ModelMapper modelMapper;

  public ArticleEntity createArticle(CreateArticleDTO createArticleDTO) {
    System.out.println("Article Service");
    ArticleEntity articleEntity = modelMapper.map(createArticleDTO, ArticleEntity.class);

    return articlesRepository.save(articleEntity);
  }

  
}
