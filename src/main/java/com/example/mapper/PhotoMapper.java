package com.example.mapper;

import com.example.dto.PhotoDto;
import com.example.entity.Photos;

public class PhotoMapper {

    public static PhotoDto mapPhotosToPhotosDto(Photos photos,PhotoDto photoDto){
        photoDto.setImageUrl(photos.getImageUrl());
        return photoDto;
    }
}
