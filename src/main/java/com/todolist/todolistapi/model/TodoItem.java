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
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @OneToOne(mappedBy = "todoItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Details details;

    @ManyToMany(mappedBy = "assignedTodos")
    private List<User> assignedUsers;

    public TodoItem(String title, String description, Details details) {
        this.title = title;
        this.description = description;
        this.details = details;
    }

    public TodoItem(Long id, String title, String description, Details details, List<User> assignedUsers) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.details = details;
        this.assignedUsers = assignedUsers;
    }

    public TodoItem(String title, String description, Details details, List<User> assignedUsers) {
        this.title = title;
        this.description = description;
        this.details = details;
        this.assignedUsers = assignedUsers;
    }

}
