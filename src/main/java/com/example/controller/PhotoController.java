package com.example.controller;

import com.example.dto.PhotoDto;
import com.example.entity.Photos;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.PhotoMapper;
import com.example.projection.AveragePhotoPerUser;
import com.example.projection.PhotoLikes;
import com.example.respository.PhotosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Limit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PhotoController {

    private final PhotosRepository photosRepository;

    @Autowired
    public PhotoController(PhotosRepository photosRepository){
        this.photosRepository = photosRepository;
    }

    @GetMapping("/photos/{id}")
    public ResponseEntity<PhotoDto> getPhotos(@PathVariable("id") Long id){
        Photos photos = photosRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Photos", "id", String.valueOf(id)));
        PhotoDto photoDto = PhotoMapper.mapPhotosToPhotosDto(photos, new PhotoDto());
        return ResponseEntity.status(HttpStatus.OK).body(photoDto);
    }

    @GetMapping("/users/{userId}/photos")
    public ResponseEntity<List<PhotoDto>> getPhotosByUsers(@PathVariable("userId") Long userId){
        List<Photos> photos = photosRepository.findByUsers_Id(userId);
        List<PhotoDto> photoDto = photos.stream().map(photo -> PhotoMapper.mapPhotosToPhotosDto(photo, new PhotoDto())).collect(Collectors.toCollection(ArrayList::new));
        return ResponseEntity.status(HttpStatus.OK).body(photoDto);
    }

    @GetMapping("/photos/most-liked")
    public ResponseEntity<PhotoLikes> getPhotosWhichGetMostLiked(){
        PhotoLikes photoLikes = photosRepository.findPhotoWhoGetMostLike(Limit.of(1));
        return ResponseEntity.status(HttpStatus.OK).body(photoLikes);
    }

    @GetMapping("/avg-photo-per-user")
    public ResponseEntity<AveragePhotoPerUser> calculateAveragePhotoPerUser(){
        AveragePhotoPerUser averagePhotoPerUser = photosRepository.calculateAveragePhotoPerUser();
        return ResponseEntity.status(HttpStatus.OK).body(averagePhotoPerUser);
    }
}
