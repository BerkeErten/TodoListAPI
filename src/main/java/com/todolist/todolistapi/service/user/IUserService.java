package com.todolist.todolistapi.service.user;

import com.todolist.todolistapi.dto.UserDto;
import com.todolist.todolistapi.model.User;
import com.todolist.todolistapi.request.AddUserRequest;

import java.util.List;

public interface IUserService {

    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    UserDto addUser(AddUserRequest userRequest);

    //TODO: void yerine UserDto return edebilir
    // update ve delete
    void updateUserById(Long id);
    void deleteUserById(Long id);
    void assignTodoItemToUser(Long todoitem_id, Long user_id);

    UserDto convertToDto(User user);
}
