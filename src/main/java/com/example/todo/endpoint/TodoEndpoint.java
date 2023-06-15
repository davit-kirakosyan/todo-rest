package com.example.todo.endpoint;


import com.example.todo.dto.todoDto.CreateTodoRequestDto;
import com.example.todo.dto.todoDto.CreateTodoResponseDto;
import com.example.todo.dto.todoDto.TodoDto;
import com.example.todo.security.CurrentUser;
import com.example.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoEndpoint {

    private final TodoService todoService;

    @PostMapping("/add")
    public ResponseEntity<CreateTodoResponseDto> addTodo(@RequestBody CreateTodoRequestDto createTodoRequestDto,
                                                         @AuthenticationPrincipal CurrentUser currentUser) {
        return todoService.createTodo(createTodoRequestDto, currentUser);
    }

    @GetMapping()
    public ResponseEntity<List<TodoDto>> getToDo(@AuthenticationPrincipal CurrentUser currentUser) {
        return todoService.getTodoUser(currentUser);
    }
}
