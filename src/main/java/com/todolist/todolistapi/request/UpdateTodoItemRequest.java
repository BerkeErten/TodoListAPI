package com.todolist.todolistapi.request;

import com.todolist.todolistapi.model.Details;
import lombok.Data;

@Data
public class UpdateTodoItemRequest {
    private String title;
    private String description;
    private Details details;

}
