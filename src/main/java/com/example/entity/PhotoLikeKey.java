package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class PhotoLikeKey implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "photo_id")
    private Long photoId;
}
