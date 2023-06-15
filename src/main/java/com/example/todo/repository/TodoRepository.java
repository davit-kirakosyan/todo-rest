package com.example.todo.repository;

import com.example.todo.entity.ToDo;
import com.example.todo.entity.type.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<ToDo, Integer> {

    List<ToDo> findByUserId(int userId);

    Optional<ToDo> findByCategoryId(int categoryId);

    Optional<List<ToDo>> findByUserIdAndCategoryId(int userId, int categoryId);

    Optional<List<ToDo>> findByUserIdAndStatus(int userId, Status status);
}
