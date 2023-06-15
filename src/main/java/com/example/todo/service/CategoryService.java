package com.example.todo.service;

import com.example.todo.dto.categoryDto.CategoryDto;
import com.example.todo.dto.categoryDto.CreateCategoryRequestDto;
import com.example.todo.dto.categoryDto.CreateCategoryResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    ResponseEntity<List<CategoryDto>> getAll();

    ResponseEntity<CreateCategoryResponseDto> createCategory(CreateCategoryRequestDto createCategoryRequestDto);

    ResponseEntity<?> deleteById(int id);
}
