package com.example.todo.mapper;

import com.example.todo.dto.userDto.CreateUserRequestDto;
import com.example.todo.dto.userDto.UserDto;
import com.example.todo.dto.userDto.UserResponseDto;
import com.example.todo.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User map(CreateUserRequestDto dto);
    UserResponseDto map(User entity);

    UserDto mapToDto(User entity);

}