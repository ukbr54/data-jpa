package com.example.respository;

import com.example.entity.Photos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotosRepository extends JpaRepository<Photos,Long> {
    List<Photos> findByUsers_Id(Long id);

}
