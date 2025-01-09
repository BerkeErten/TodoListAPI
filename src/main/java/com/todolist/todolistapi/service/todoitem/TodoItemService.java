package com.todolist.todolistapi.service.todoitem;

import com.todolist.todolistapi.dto.TodoItemDto;
import com.todolist.todolistapi.model.Details;
import com.todolist.todolistapi.model.TodoItem;
import com.todolist.todolistapi.repository.DetailsRepository;
import com.todolist.todolistapi.repository.TodoItemRepository;
import com.todolist.todolistapi.request.AddTodoItemRequest;
import com.todolist.todolistapi.request.UpdateDetailsRequest;
import com.todolist.todolistapi.request.UpdateTodoItemRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoItemService implements ITodoItemService{
    private TodoItemRepository todoItemRepository;
    private DetailsRepository detailsRepository;

    @Override
    public TodoItemDto getTodoItemById(Long id) {
        TodoItem todoItem = todoItemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Todo item not found with id: " + id));
        return convertToDto(todoItem);
    }

    @Override
    public TodoItemDto addTodoItem(AddTodoItemRequest todoItemRequest){
        Details details = todoItemRequest.getDetails();

        if(details != null){
          details.setTodoItem(null);
        }

        TodoItem todoItem = createTodoItem(todoItemRequest);

        if(details != null){
            details.setTodoItem(todoItem);
        }
        return convertToDto(todoItemRepository.save(todoItem));
    }

    public TodoItem createTodoItem(AddTodoItemRequest request){
        return new TodoItem(
                request.getTitle(),
                request.getDescription(),
                request.getDetails()
        );
    }

    @Override
    public void deleteTodoItemById(Long id) {
        todoItemRepository.findById(id).ifPresent(todoItemRepository::delete);
    }



    //TODO: Details ve todoitem repolarındançekilerek update edilmeli
    //FIXME: need testing
    @Override
    public TodoItemDto updateTodoItem(UpdateTodoItemRequest request, Long todoItemId) {
        TodoItem todoItem = todoItemRepository.findById(todoItemId).orElseThrow(() -> new EntityNotFoundException("Updated item does not exist with id: "+todoItemId));
        Details details = detailsRepository.findByTodoItem_Id(todoItemId);

        if(request.getTitle() != null){
            todoItem.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            todoItem.setDescription(request.getDescription());
        }
        if (request.getDetails() != null) {
            details.setDeadline(request.getDetails().getDeadline() != null ? request.getDetails().getDeadline() : details.getDeadline());
            details.setPriority(request.getDetails().getPriority() != null ? request.getDetails().getPriority() : details.getPriority());
            details.setSeverity(request.getDetails().getSeverity() != null ? request.getDetails().getSeverity() : details.getSeverity());
        }

        todoItemRepository.save(todoItem);
        detailsRepository.save(details);
        return convertToDto(todoItem);
    }

    @Override
    public TodoItemDto convertToDto(TodoItem todoItem) {
        TodoItemDto todoItemDto = new TodoItemDto();
        todoItemDto.setId(todoItem.getId());
        todoItemDto.setTitle(todoItem.getTitle());
        todoItemDto.setDescription(todoItem.getDescription());

        if (todoItem.getDetails() != null) {
            todoItemDto.setDeadline(todoItem.getDetails().getDeadline());
            todoItemDto.setSeverity(todoItem.getDetails().getSeverity());
            todoItemDto.setPriority(todoItem.getDetails().getPriority());
        }


        return todoItemDto;
    }

    @Override
    public List<TodoItemDto> getAllTodoItems() {
        List<TodoItem> todoItems = todoItemRepository.findAll();

        return todoItems.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //FIXME: need testing
    @Override
    public TodoItemDto updateDetails( UpdateDetailsRequest request, Long todoItemId) {
            TodoItem todoItem = todoItemRepository.findById(todoItemId).orElseThrow(() -> new EntityNotFoundException("Todo item not found with id: " + todoItemId));
            Details details = new Details();

        if (request != null) {
            details.setDeadline(request.getDeadline() != null ? request.getDeadline() : details.getDeadline());
            details.setPriority(request.getPriority() != null ? request.getPriority() : details.getPriority());
            details.setSeverity(request.getSeverity() != null ? request.getSeverity() : details.getSeverity());
        }
        todoItem.setDetails(details);
        details.setTodoItem(todoItem);
        todoItemRepository.save(todoItem);
        detailsRepository.save(details);

        return convertToDto(todoItem);
    }
}
