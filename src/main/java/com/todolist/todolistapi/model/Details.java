package com.todolist.todolistapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate deadline;
    private String priority;
    private String severity;
    @OneToOne
    @JoinColumn(name ="todoitem_id", nullable = false)
    private TodoItem todoItem;

    public Details(Long id, LocalDate deadline, String priority, String severity, TodoItem todoItem) {
        this.id = id;
        this.deadline = deadline;
        this.priority = priority;
        this.severity = severity;
        this.todoItem = todoItem;
    }

    public Details(LocalDate deadline, String priority, String severity, TodoItem todoItem) {
        this.deadline = deadline;
        this.priority = priority;
        this.severity = severity;
        this.todoItem = todoItem;
    }
}
