package com.todolist.todolistapi.service.todoitem;

import com.todolist.todolistapi.dto.TodoItemDto;
import com.todolist.todolistapi.model.TodoItem;
import com.todolist.todolistapi.request.AddTodoItemRequest;
import com.todolist.todolistapi.request.UpdateDetailsRequest;
import com.todolist.todolistapi.request.UpdateTodoItemRequest;

import java.util.List;

public interface ITodoItemService {
    TodoItemDto getTodoItemById(Long id);
    TodoItemDto addTodoItem(AddTodoItemRequest todoItemRequest);
    void deleteTodoItemById(Long id);
    TodoItemDto updateTodoItem(UpdateTodoItemRequest request, Long todoItemId);
    TodoItemDto convertToDto(TodoItem todoItem);
    List<TodoItemDto> getAllTodoItems();
    TodoItemDto updateDetails(UpdateDetailsRequest request, Long todoItemId);
}
