package com.example.todo.dto.todoDto;


import com.example.todo.entity.Category;
import com.example.todo.entity.User;
import com.example.todo.entity.type.Status;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDto {

    private int id;
    private String title;
    private Status status;
    private Category category;
    private User user;
}
