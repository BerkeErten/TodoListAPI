package com.todolist.todolistapi.repository;

import com.todolist.todolistapi.model.Details;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailsRepository extends JpaRepository<Details, Long> {
    Details findByTodoItem_Id(Long id);
}
