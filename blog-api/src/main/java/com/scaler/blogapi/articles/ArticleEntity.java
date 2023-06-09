package com.scaler.blogapi.articles;

import java.util.List;

import com.scaler.blogapi.commons.BaseEntity;
import com.scaler.blogapi.users.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "articles")
@Getter
@Setter
public class ArticleEntity extends BaseEntity {

  @Column(nullable = false, length = 200)
  String title;

  @Column(unique = true, nullable = false, length = 150)
  String slug;

  String subTitle;

  @Column(nullable = false, length = 8000)
  String body;
  // String[] tagList; TODO: Implement this

  @ManyToOne
  UserEntity author;

  @ManyToMany
  @JoinTable(
    name = "article_likes",
    joinColumns = @JoinColumn(name = "article_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id")
  )
  List<UserEntity> likedBy;
}
