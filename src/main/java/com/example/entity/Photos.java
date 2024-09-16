package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 *   Owner - ManyToOne is defined in this class so this class is responsible for adding and updating the relationship
 */

@Entity
@Table(name = "photos")
@Setter @Getter
@ToString(exclude = {"users"})
@AllArgsConstructor @NoArgsConstructor
public class Photos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "image_url",nullable = false)
    private String imageUrl;
    @ManyToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private Users users;
    private LocalDateTime createdAt;
}
