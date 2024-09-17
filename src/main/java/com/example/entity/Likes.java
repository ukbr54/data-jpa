package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "likes")
public class Likes {

    @EmbeddedId
    private PhotoLikeKey id;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Users users;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Photos photos;

    private LocalDateTime createdAt;

}
