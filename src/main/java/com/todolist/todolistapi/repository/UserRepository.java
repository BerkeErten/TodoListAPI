package com.todolist.todolistapi.repository;

import com.todolist.todolistapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
