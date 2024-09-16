package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 *  Inverse-end : This class will not be responsible for any update and insert of any relationship
 */

@Entity
@Table(name = "users")
@Setter @Getter
@ToString(exclude = {"photos"})
@AllArgsConstructor @NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String username;
    @OneToMany(mappedBy = "users",fetch = FetchType.LAZY)
    private Set<Photos> photos = new HashSet<>();
    private LocalDateTime createdAt;

    /**
     *  generic method to care about relationship
     */
    public void addPhotos(Photos photo){
        this.photos.add(photo);
        photo.setUsers(this);
    }

    public void removePhotos(Photos photo){
        this.photos.remove(photo);
        photo.setUsers(null);
    }
}
