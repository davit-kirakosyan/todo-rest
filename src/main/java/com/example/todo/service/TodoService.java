package com.example.todo.service;

import com.example.todo.dto.todoDto.CreateTodoRequestDto;
import com.example.todo.dto.todoDto.CreateTodoResponseDto;
import com.example.todo.dto.todoDto.TodoDto;
import com.example.todo.entity.Category;
import com.example.todo.entity.ToDo;
import com.example.todo.entity.User;
import com.example.todo.entity.type.Status;
import com.example.todo.security.CurrentUser;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TodoService {
    ResponseEntity<CreateTodoResponseDto> createTodo(CreateTodoRequestDto createTodoRequestDto, CurrentUser currentUser);

    ResponseEntity<List<TodoDto>> getTodoUser(CurrentUser currentUser);

    ResponseEntity<List<CreateTodoResponseDto>> getTodoStatus(Status status, CurrentUser currentUser);

    ResponseEntity<List<CreateTodoResponseDto>> getTodoCategory(Category category, CurrentUser currentUser);


    ResponseEntity<TodoDto> updateStatus(CurrentUser currentUser, ToDo toDo);

    ResponseEntity<?> deleteById(int id, CurrentUser currentUser);
}
