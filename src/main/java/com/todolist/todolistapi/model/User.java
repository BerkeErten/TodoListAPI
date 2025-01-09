package com.todolist.todolistapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

    @ManyToMany
    @JoinTable(name = "user_todoitem", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "todoitem_id"))
    private List<TodoItem> assignedTodos;

    public User(Long id, String name, String surname, List<TodoItem> assignedTodos) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.assignedTodos = assignedTodos;
    }

    public User(String name, String surname, List<TodoItem> assignedTodos) {
        this.name = name;
        this.surname = surname;
        this.assignedTodos = assignedTodos;
    }
    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public void assignTodoItem(TodoItem todoItem){
        if(!assignedTodos.contains(todoItem)){
            assignedTodos.add(todoItem);
        }

    }
}