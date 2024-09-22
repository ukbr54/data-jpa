package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "photo_tags")
@IdClass(PhotoTagId.class)
public class PhotoTag {

    @Id
    @Column(name = "photo_id")
    private Long photoId;

    @Id
    @Column(name = "tag_id")
    private Long tagId;
}
