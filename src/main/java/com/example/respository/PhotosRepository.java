package com.example.respository;

import com.example.entity.Photos;
import com.example.projection.PhotoLikes;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhotosRepository extends JpaRepository<Photos,Long> {
    /**
     * This method is used to find the list of photos using user_id
     */
    List<Photos> findByUsers_Id(Long id);

    /**
     * SELECT users.username,photos.id,image_url,count(*) as total
     * FROM photos INNER JOIN likes
     * ON photos.id = likes.photo_id
     * INNER JOIN users
     * ON photos.user_id = users.id
     * GROUP BY photos.id
     * ORDER BY total DESC LIMIT 1;
     */
    @Query("SELECT new com.example.projection.PhotoLikes(p.id,u.username,p.imageUrl,COUNT(p) as total) FROM Photos p JOIN p.likes l JOIN p.users u GROUP BY p.id ORDER BY total DESC")
    PhotoLikes findPhotoWhoGetMostLike(Limit limit);
}
