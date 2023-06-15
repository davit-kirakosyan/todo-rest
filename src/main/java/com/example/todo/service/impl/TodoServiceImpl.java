package com.example.todo.service.impl;

import com.example.todo.dto.todoDto.CreateTodoRequestDto;
import com.example.todo.dto.todoDto.CreateTodoResponseDto;
import com.example.todo.dto.todoDto.TodoDto;
import com.example.todo.dto.userDto.UserDto;
import com.example.todo.entity.Category;
import com.example.todo.entity.ToDo;
import com.example.todo.entity.User;
import com.example.todo.entity.type.Status;
import com.example.todo.mapper.CategoryMapper;
import com.example.todo.mapper.TodoMapper;
import com.example.todo.repository.TodoRepository;
import com.example.todo.security.CurrentUser;
import com.example.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;
    private final CategoryMapper categoryMapper;


    @Override
    public ResponseEntity<CreateTodoResponseDto> createTodo(CreateTodoRequestDto createTodoRequestDto, CurrentUser currentUser) {
        return ResponseEntity.ok(todoMapper.map(todoRepository.save(ToDo.builder()
                .title(createTodoRequestDto.getTitle())
                .status(Status.NOT_STARTED)
                .category(createTodoRequestDto.getCategory())
                .user(currentUser.getUser())
                .build())));
    }

    @Override
    public ResponseEntity<List<TodoDto>> getTodoUser(CurrentUser currentUser) {
        List<ToDo> toDoByUser = todoRepository.findByUserId(currentUser.getUser().getId());
        if (toDoByUser.isEmpty()) {
            return null;
        }
        List<TodoDto> todoDtos = new ArrayList<>();
        for (ToDo toDo : toDoByUser) {
            todoDtos.add(todoMapper.mapToDto(toDo));
        }
        return ResponseEntity.ok(todoDtos);
    }


    @Override
    public ResponseEntity<List<CreateTodoResponseDto>> getTodoCategory(Category category, CurrentUser currentUser) {
        Optional<List<ToDo>> byUserIdAndCategoryId = todoRepository.findByUserIdAndCategoryId(category.getId(), currentUser.getUser().getId());
        if (byUserIdAndCategoryId.isEmpty() || byUserIdAndCategoryId.get().size() == 0){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(todoMapper.mapToDtoList(byUserIdAndCategoryId.get()));
    }


    @Override
    public ResponseEntity<List<CreateTodoResponseDto>> getTodoStatus(Status status, CurrentUser currentUser) {
        Optional<List<ToDo>> byUserIdAndCategoryId = todoRepository.findByUserIdAndStatus(currentUser.getUser().getId(),status);
        if (byUserIdAndCategoryId.isEmpty() || byUserIdAndCategoryId.get().size() == 0){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(todoMapper.mapToDtoList(byUserIdAndCategoryId.get()));
    }

    @Override
    public ResponseEntity<TodoDto> updateStatus(CurrentUser currentUser, ToDo toDo) {
        Optional<ToDo> byId = todoRepository.findById(toDo.getId());
        if (byId.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ToDo todo = byId.get();
        if (todo.getUser().getId() != currentUser.getUser().getId()) {
            return ResponseEntity.notFound().build();
        }
        if (todo.getStatus() == Status.DONE) {
            return ResponseEntity.ok(todoMapper.mapToDto(todo));
        }
        if (todo.getStatus() == Status.IN_PROGRESS) {
            todo.setStatus(Status.DONE);
        }
        if (todo.getStatus() == Status.NOT_STARTED) {
            todo.setStatus(Status.IN_PROGRESS);
        }
        todoRepository.save(todo);
        return ResponseEntity.ok(todoMapper.mapToDto(todo));
    }

    @Override
    public ResponseEntity<?> deleteById(int id, CurrentUser currentUser) {
        Optional<ToDo> byId = todoRepository.findById(id);
        if (byId.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        if (byId.get().getUser().getId() == currentUser.getUser().getId()) {
            todoRepository.deleteById(id);
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.noContent().build();
    }
}
