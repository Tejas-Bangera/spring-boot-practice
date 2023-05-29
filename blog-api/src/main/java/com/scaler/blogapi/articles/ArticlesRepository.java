package com.scaler.blogapi.articles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ArticlesRepository extends JpaRepository<ArticleEntity, Integer> {
  List<ArticleEntity> findBySlug(String article_slug);
}
