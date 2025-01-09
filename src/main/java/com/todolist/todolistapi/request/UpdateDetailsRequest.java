package com.todolist.todolistapi.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateDetailsRequest {
    private LocalDate deadline;
    private String priority;
    private String severity;
}
