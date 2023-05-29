package com.scaler.blogapi.articles;

import java.net.URI;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scaler.blogapi.articles.ArticlesService.DuplicateArticleSlugException;
import com.scaler.blogapi.articles.dto.ArticleResponseDTO;
import com.scaler.blogapi.articles.dto.CreateArticleDTO;
import com.scaler.blogapi.commons.ErrorResponseDTO;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticlesController {
  private final ArticlesService articlesService;
  private final ModelMapper modelMapper;

  @PostMapping("")
  public ResponseEntity<ArticleResponseDTO> createArticle(@RequestBody CreateArticleDTO createArticleDTO) {
    ArticleEntity articleEntity = articlesService.createArticle(createArticleDTO);
    ArticleResponseDTO articleResponseDTO = modelMapper.map(articleEntity, ArticleResponseDTO.class);

    return ResponseEntity.created(URI.create("/articles/" + articleEntity.getId())).body(articleResponseDTO);
  }

  @GetMapping("")
  public ResponseEntity<List<ArticleResponseDTO>> getAllArticles() {
    return ResponseEntity.ok(articlesService.getAllArticles());
  }

  @GetMapping("/{article_slug}")
  public ResponseEntity<ArticleResponseDTO> getArticleBySlug(@PathVariable String article_slug) {
    return ResponseEntity.ok(articlesService.getArticleBySlug(article_slug));
  }

  @ExceptionHandler(DuplicateArticleSlugException.class)
  public ResponseEntity<ErrorResponseDTO> handleErrors(DuplicateArticleSlugException errorMessage) {
    return new ResponseEntity<ErrorResponseDTO>(new ErrorResponseDTO(errorMessage.getMessage()), HttpStatus.CONFLICT);
  }

}
