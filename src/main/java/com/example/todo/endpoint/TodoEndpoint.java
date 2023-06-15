package com.example.todo.endpoint;


import com.example.todo.dto.todoDto.CreateTodoRequestDto;
import com.example.todo.dto.todoDto.CreateTodoResponseDto;
import com.example.todo.dto.todoDto.TodoDto;
import com.example.todo.entity.Category;
import com.example.todo.entity.ToDo;
import com.example.todo.entity.type.Status;
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


    @GetMapping("/byStatus")
    public ResponseEntity<List<CreateTodoResponseDto>> getStatus(@RequestParam("status") Status status,
                                                                 @AuthenticationPrincipal CurrentUser currentUser) {
        return todoService.getTodoStatus(status, currentUser);
    }

    @GetMapping("/byCategory")
    public ResponseEntity<List<CreateTodoResponseDto>> getStatus(@RequestParam("category") Category category,
                                                                 @AuthenticationPrincipal CurrentUser currentUser) {
        return todoService.getTodoCategory(category, currentUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> update(@AuthenticationPrincipal CurrentUser currentUser,
                                          @PathVariable ToDo toDo) {
        return todoService.updateStatus(currentUser, toDo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@AuthenticationPrincipal CurrentUser currentUser,
                                        @PathVariable("id") int id) {
        return todoService.deleteById(id, currentUser);
    }
}
