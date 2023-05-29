package com.scaler.blogapi.articles;

import java.net.URI;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scaler.blogapi.articles.dto.ArticleResponseDTO;
import com.scaler.blogapi.articles.dto.CreateArticleDTO;

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

}
