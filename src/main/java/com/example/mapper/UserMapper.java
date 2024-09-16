package com.example.mapper;

import com.example.dto.UserDto;
import com.example.entity.Users;

public class UserMapper {

    public static UserDto mapToUserDto(Users users, UserDto userDto){
        userDto.setUsername(users.getUsername());
        return userDto;
    }
}
