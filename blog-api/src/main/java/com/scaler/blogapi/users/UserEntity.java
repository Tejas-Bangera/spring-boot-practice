package com.scaler.blogapi.users;

import java.util.List;

import com.scaler.blogapi.articles.ArticleEntity;
import com.scaler.blogapi.commons.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "users")
@Setter
@Getter
public class UserEntity extends BaseEntity {

  @Column(unique = true, nullable = false, length = 50)
  String username;
  String email;
  String password; // TODO: Hash this
  String bio;
  String image;

  @ManyToMany(mappedBy = "likedBy")
  List<ArticleEntity> likedArticles;

  @ManyToMany
  @JoinTable(
    name = "user_follows",
    joinColumns = @JoinColumn(name = "follower_id"),
    inverseJoinColumns = @JoinColumn(name = "following_id")
  )
  List<UserEntity> following;

  @ManyToMany(mappedBy = "following")
  List<UserEntity> followers;
}
