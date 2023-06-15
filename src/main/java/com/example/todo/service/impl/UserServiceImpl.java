package com.example.todo.service.impl;

import com.example.todo.dto.userDto.*;
import com.example.todo.entity.User;
import com.example.todo.entity.type.Role;
import com.example.todo.mapper.UserMapper;
import com.example.todo.repository.UserRepository;
import com.example.todo.security.CurrentUser;
import com.example.todo.service.UserService;
import com.example.todo.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil tokenUtil;
    private final UserMapper userMapper;


    @Override
    public ResponseEntity<UserAuthResponseDto> auth(UserAuthRequestDto userAuthRequestDto) {
        Optional<User> byEmail = userRepository.findByEmail(userAuthRequestDto.getEmail());
        if (byEmail.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        User user = byEmail.get();
        if (!passwordEncoder.matches(userAuthRequestDto.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = tokenUtil.generateToken(userAuthRequestDto.getEmail());
        return ResponseEntity.ok(new UserAuthResponseDto(token));
    }

    @Override
    public ResponseEntity<UserDto> register(CreateUserRequestDto createUserRequestDto) {
        Optional<User> byEmail = userRepository.findByEmail(createUserRequestDto.getEmail());
        if (byEmail.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        User user = userMapper.map(createUserRequestDto);
        user.setPassword(passwordEncoder.encode(createUserRequestDto.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        return ResponseEntity.ok(userMapper.mapToDto(user));
    }

    @Override
    public ResponseEntity<UserDto> update(int id, User user, CurrentUser currentUser) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
        if (byEmail.isPresent() && byEmail.get().getId() != id) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        User userFromDB = byId.get();
        if (userFromDB.getId() == currentUser.getUser().getId()) {
            if (user.getName() != null && !user.getName().isEmpty()) {
                userFromDB.setName(user.getName());
            }

            if (user.getSurname() != null && !user.getSurname().isEmpty()) {
                userFromDB.setSurname(user.getSurname());
            }

            if (user.getEmail() != null && !user.getEmail().isEmpty()) {
                userFromDB.setEmail(user.getEmail());
            }

            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                userFromDB.setPassword(passwordEncoder.encode(user.getPassword()));
            }
        }

        return ResponseEntity.ok(userMapper.mapToDto(userRepository.save(userFromDB)));
    }

    @Override
    public ResponseEntity<UserResponseDto> getById(int id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(userMapper.map(byId.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> deleteById(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }
}
