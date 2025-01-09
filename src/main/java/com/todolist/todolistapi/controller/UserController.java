package com.todolist.todolistapi.controller;

import com.todolist.todolistapi.dto.UserDto;
import com.todolist.todolistapi.request.AddUserRequest;
import com.todolist.todolistapi.response.ApiResponse;
import com.todolist.todolistapi.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {
    private IUserService userService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllUsers(){
        try{
            List<UserDto> userList =  userService.getAllUsers();

            ApiResponse response = new ApiResponse("Fetched all users: ", userList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){

            ApiResponse errorResponse = new ApiResponse("Failed to fetch all users", null);

            return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long userId){
        try {
            UserDto user = userService.getUserById(userId);

            return new ResponseEntity<>(new ApiResponse("User retrieved successfully", user), HttpStatus.OK);
        }catch (Exception e){
            ApiResponse errorResponse = new ApiResponse("Failed to retrieve user", null);

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> saveUser(@RequestBody AddUserRequest userRequest){
        try{
            UserDto user = userService.addUser(userRequest);

            return new ResponseEntity<>(new ApiResponse("User saved successfully", user),HttpStatus.OK);
        }catch (Exception e){
            ApiResponse errorResponse = new ApiResponse("Failed to add user", null);

            return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}