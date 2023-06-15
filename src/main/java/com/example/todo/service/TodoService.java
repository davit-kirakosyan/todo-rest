package com.example.todo.service;

import com.example.todo.dto.todoDto.CreateTodoRequestDto;
import com.example.todo.dto.todoDto.CreateTodoResponseDto;
import com.example.todo.dto.todoDto.TodoDto;
import com.example.todo.entity.User;
import com.example.todo.security.CurrentUser;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TodoService {
    ResponseEntity<CreateTodoResponseDto> createTodo(CreateTodoRequestDto createTodoRequestDto, CurrentUser currentUser);

    ResponseEntity<List<TodoDto>> getTodoUser(CurrentUser currentUser);

    ResponseEntity<?> deleteById(int id, CurrentUser currentUser);
}
