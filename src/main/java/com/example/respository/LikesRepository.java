package com.example.respository;

import com.example.entity.Likes;
import com.example.entity.PhotoLikeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, PhotoLikeKey> {
}
