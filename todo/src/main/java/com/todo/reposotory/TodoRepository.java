package com.todo.reposotory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.entity.Todo;



public interface TodoRepository extends JpaRepository<Todo, Long> {

}
