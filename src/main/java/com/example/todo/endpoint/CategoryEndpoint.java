package com.example.todo.endpoint;


import com.example.todo.dto.categoryDto.CategoryDto;
import com.example.todo.dto.categoryDto.CreateCategoryRequestDto;
import com.example.todo.dto.categoryDto.CreateCategoryResponseDto;
import com.example.todo.security.CurrentUser;
import com.example.todo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryEndpoint {

    private final CategoryService categoryService;


    @GetMapping()
    public ResponseEntity<List<CategoryDto>> categoryAll() {
        return categoryService.getAll();
    }


    @PostMapping("/add")
    public ResponseEntity<CreateCategoryResponseDto> categoryAdd(@RequestBody CreateCategoryRequestDto createCategoryRequestDto) {
        return categoryService.createCategory(createCategoryRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) {
        return categoryService.deleteById(id);
    }

}
