package com.example.todo.service.impl;

import com.example.todo.dto.categoryDto.CategoryDto;
import com.example.todo.dto.categoryDto.CreateCategoryRequestDto;
import com.example.todo.dto.categoryDto.CreateCategoryResponseDto;
import com.example.todo.entity.Category;
import com.example.todo.mapper.CategoryMapper;
import com.example.todo.repository.CategoryRepository;
import com.example.todo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    public ResponseEntity<List<CategoryDto>> getAll() {
        List<Category> all = categoryRepository.findAll();
        if (all.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category : all) {
            categoryDtos.add(categoryMapper.mapToDto(category));
        }
        return ResponseEntity.ok(categoryDtos);
    }

    @Override
    public ResponseEntity<CreateCategoryResponseDto> createCategory(CreateCategoryRequestDto createCategoryRequestDto) {
        Category category = categoryMapper.map(createCategoryRequestDto);
        return ResponseEntity.ok(categoryMapper.map(categoryRepository.save(category)));
    }

    @Override
    public ResponseEntity<?> deleteById(int id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
