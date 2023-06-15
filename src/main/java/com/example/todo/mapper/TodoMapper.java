package com.example.todo.mapper;


import com.example.todo.dto.todoDto.CreateTodoRequestDto;
import com.example.todo.dto.todoDto.CreateTodoResponseDto;
import com.example.todo.dto.todoDto.TodoDto;
import com.example.todo.entity.ToDo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    ToDo map(CreateTodoRequestDto dto);


    TodoDto mapToDto(ToDo entity);

    CreateTodoResponseDto map(ToDo entity);

    List<CreateTodoResponseDto> mapToDtoList(List<ToDo> todoDtoList);


}
