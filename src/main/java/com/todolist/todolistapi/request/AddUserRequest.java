package com.todolist.todolistapi.request;

import lombok.Data;

@Data
public class AddUserRequest {
    private String name;
    private String surname;
}
