package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhotoDto {
    private String imageUrl;
    private UserDto user;
}
