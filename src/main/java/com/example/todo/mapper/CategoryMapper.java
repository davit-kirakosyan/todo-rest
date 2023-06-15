package com.example.todo.mapper;


import com.example.todo.dto.categoryDto.CategoryDto;
import com.example.todo.dto.categoryDto.CreateCategoryRequestDto;
import com.example.todo.dto.categoryDto.CreateCategoryResponseDto;
import com.example.todo.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category map(CreateCategoryRequestDto dto);


    CategoryDto mapToDto(Category entity);

    CreateCategoryResponseDto map(Category entity);

}
