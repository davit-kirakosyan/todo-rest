package com.example.todo.service;

import com.example.todo.dto.userDto.*;
import com.example.todo.entity.User;
import com.example.todo.security.CurrentUser;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<UserAuthResponseDto> auth(UserAuthRequestDto userAuthRequestDto);

    ResponseEntity<UserDto> register(CreateUserRequestDto createUserRequestDto);

    ResponseEntity<UserDto> update(int id, User user, CurrentUser currentUser);

    ResponseEntity<UserResponseDto> getById(int id);

    ResponseEntity<?> deleteById(int id);
}
