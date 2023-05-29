package com.scaler.blogapi.articles;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.scaler.blogapi.articles.dto.ArticleResponseDTO;
import com.scaler.blogapi.articles.dto.CreateArticleDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArticlesService {
  private final ArticlesRepository articlesRepository;
  private final ModelMapper modelMapper;

  public ArticleEntity createArticle(CreateArticleDTO createArticleDTO) {
    List<ArticleEntity> articlesBySlug = articlesRepository.findBySlug(createArticleDTO.getSlug());

    if(!articlesBySlug.isEmpty()) throw new DuplicateArticleSlugException(createArticleDTO.getSlug());

    ArticleEntity articleEntity = modelMapper.map(createArticleDTO, ArticleEntity.class);

    return articlesRepository.save(articleEntity);
  }

  public List<ArticleResponseDTO> getAllArticles() {
    List<ArticleEntity> articles = articlesRepository.findAll();
    return mapArticleEntityWithResponseDTO(articles);
  }

  public static class DuplicateArticleSlugException extends IllegalArgumentException {
    public DuplicateArticleSlugException(String slug) {
      super("Article with slug " +  slug + " already exists!");
    }
  }

  public ArticleResponseDTO getArticleBySlug(String article_slug) {
    List<ArticleEntity> articlesBySlug = articlesRepository.findBySlug(article_slug);

    return mapArticleEntityWithResponseDTO(articlesBySlug).get(0);
  }

  private List<ArticleResponseDTO> mapArticleEntityWithResponseDTO(List<ArticleEntity> articleEntities) {
    List<ArticleResponseDTO> articleResponseDTOs = new ArrayList<>();

    for(ArticleEntity articleEntity : articleEntities) {
      articleResponseDTOs.add(modelMapper.map(articleEntity, ArticleResponseDTO.class));
    }

    return articleResponseDTOs;
  }
}
