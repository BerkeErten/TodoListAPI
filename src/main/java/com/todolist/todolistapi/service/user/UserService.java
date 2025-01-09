package com.todolist.todolistapi.service.user;

import com.todolist.todolistapi.dto.UserDto;
import com.todolist.todolistapi.model.TodoItem;
import com.todolist.todolistapi.model.User;
import com.todolist.todolistapi.repository.TodoItemRepository;
import com.todolist.todolistapi.repository.UserRepository;
import com.todolist.todolistapi.request.AddUserRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private UserRepository userRepository;
    private TodoItemRepository todoItemRepository;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new  EntityNotFoundException("User not found with id: "+ id));
        return convertToDto(user);
    }

    @Override
    public UserDto addUser(AddUserRequest userRequest) {
        User user = createUser(userRequest);
        userRepository.save(user);

        return convertToDto(user);
    }

    private User createUser(AddUserRequest userRequest){
        return new User(
                userRequest.getName(),
                userRequest.getSurname()
        );
    }

    @Override
    public void updateUserById(Long id) {

    }

    @Override
    public void deleteUserById(Long id) {

    }

    //TODO: assign user service den todoservice e taşınacak
    @Override
    public void assignTodoItemToUser(Long todoitem_id, Long user_id) {
        TodoItem todoItem = todoItemRepository.findById(todoitem_id).orElseThrow(()->new EntityNotFoundException("Todo item not found with id: "+ todoitem_id));
        User user = userRepository.findById(user_id).orElseThrow(()->new EntityNotFoundException("User not found with id: "+ user_id));


        // Assert that todoitem does not have the same user assigned
        if (!todoItem.getAssignedUsers().contains(user) || !user.getAssignedTodos().contains(todoItem)){
            todoItem.getAssignedUsers().add(user);
            user.getAssignedTodos().add(todoItem);

            todoItemRepository.save(todoItem);
            userRepository.save(user);
        }

    }

    @Override
    public UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        return userDto;
    }


}
