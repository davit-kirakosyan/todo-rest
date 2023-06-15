package com.example.todo.endpoint;

import com.example.todo.dto.userDto.*;
import com.example.todo.entity.User;
import com.example.todo.security.CurrentUser;
import com.example.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserEndpoint {

    private final UserService userService;

    @PostMapping("/auth")
    public ResponseEntity<UserAuthResponseDto> auth(@RequestBody UserAuthRequestDto userAuthRequestDto) {
        return userService.auth(userAuthRequestDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody CreateUserRequestDto createUserRequestDto) {
        return userService.register(createUserRequestDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> update(@PathVariable("id") int id, @RequestBody User user,
                                          @AuthenticationPrincipal CurrentUser currentUser) {
        return userService.update(id, user, currentUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable("id") int id) {
        return userService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        return userService.deleteById(id);

    }
}