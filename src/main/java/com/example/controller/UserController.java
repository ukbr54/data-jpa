package com.example.controller;

import com.example.dto.PhotoDto;
import com.example.dto.UserDto;
import com.example.entity.Users;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.PhotoMapper;
import com.example.mapper.UserMapper;
import com.example.projection.UserInfo;
import com.example.respository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Limit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<UserDto> getUserDetail(@RequestParam String username){
        Users users = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Users", "username", username));
        UserDto userDto = UserMapper.mapToUserDto(users, new UserDto());
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @GetMapping("/{limit}")
    public ResponseEntity<List<UserDto>> getOldestUser(@PathVariable("limit") int limit){
        List<Users> users = userRepository.findByOrderByCreatedAtAsc(Limit.of(limit));
        List<UserDto> userDto = users.stream().map(user -> UserMapper.mapToUserDto(user, new UserDto())).toList();
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @GetMapping("/photos")
    public ResponseEntity<UserDto> getAllPhotosByUser(@RequestParam String username){
        Users users = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Users", "username", username));
        UserDto userDto = UserMapper.mapToUserDto(users, new UserDto());
        logger.info("The OneToMany relation is lazy so when we call the collection method on it, the db query is generated and triggered");
        List<PhotoDto> photoDto = users.getPhotos().stream().map(photo -> PhotoMapper.mapPhotosToPhotosDto(photo, new PhotoDto())).toList();
        userDto.getPhotos().addAll(photoDto);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @GetMapping("/never/post/photo")
    public ResponseEntity<List<UserInfo>> getUsersWhoNeverPostedPhoto(){
        List<UserInfo> users = userRepository.findByUsersNeverPostedPhoto();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
}
