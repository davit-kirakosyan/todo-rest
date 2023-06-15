package com.example.todo.entity;


import com.example.todo.entity.type.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "todo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToDo {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String title;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;
}
