package com.todolist.todolistapi.controller;

import com.todolist.todolistapi.dto.TodoItemDto;
import com.todolist.todolistapi.request.AddTodoItemRequest;
import com.todolist.todolistapi.request.UpdateDetailsRequest;
import com.todolist.todolistapi.request.UpdateTodoItemRequest;
import com.todolist.todolistapi.response.ApiResponse;
import com.todolist.todolistapi.service.todoitem.ITodoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("{api.prefix}/todoitems")
public class TodoItemController {
    private ITodoItemService todoItemService;

    @GetMapping("/{todoitemId}")
    public ResponseEntity<ApiResponse> getTodoItemById(@PathVariable Long todoitemId){
        try{
            TodoItemDto todoItem = todoItemService.getTodoItemById(todoitemId);

            return new ResponseEntity<>(new ApiResponse("Todo item retrieved successfully", todoItem), HttpStatus.OK);
        }catch (Exception e){
            ApiResponse errorResponse = new ApiResponse("Failed to retrieve todo item: " + e.getMessage(), null);

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> saveTodoItem(@RequestBody AddTodoItemRequest todoItemRequest){
        try{
            TodoItemDto todoItem = todoItemService.addTodoItem(todoItemRequest);

            return new ResponseEntity<>(new ApiResponse("Todo item added successfully, ", todoItem), HttpStatus.OK);
        }catch (Exception e){
            ApiResponse errorResponse = new ApiResponse("Failed to add todo item: "+ e.getMessage(),null);

            return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getTodoItems() {
        try {
            List<TodoItemDto> todoItems = todoItemService.getAllTodoItems();

            return new ResponseEntity<>(new ApiResponse("Fetched all todo items", todoItems), HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse errorResponse = new ApiResponse("Failed to fetch todo items: " + e.getMessage(), null);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("update/{todoitemId}")
    public ResponseEntity<ApiResponse> updateTodoItemById(@PathVariable Long todoitemId, @RequestBody UpdateTodoItemRequest request){
        try{
            TodoItemDto todoItem = todoItemService.updateTodoItem(request,todoitemId);

            return new ResponseEntity<>(new ApiResponse("Updated todo item: ", todoItem),HttpStatus.OK);
        }catch (Exception e){
            ApiResponse errorResponse = new ApiResponse("Failed to update todo item with id: "+ todoitemId+ e.getMessage(),null);

            return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/delete/{todoitemId}")
    public ResponseEntity<ApiResponse> deleteTodoItemById(@PathVariable Long todoitemId ){
        try{
            todoItemService.deleteTodoItemById(todoitemId);

            ApiResponse response = new ApiResponse("Todo item deleted succesfully",null);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch(Exception e ){
            ApiResponse errorResponse = new ApiResponse("Failed to delete",null);
            return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
