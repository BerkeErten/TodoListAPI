package com.todolist.todolistapi.repository;

import com.todolist.todolistapi.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoItemRepository extends JpaRepository<TodoItem,Long> {
}
