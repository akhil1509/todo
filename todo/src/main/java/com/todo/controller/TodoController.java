package com.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.todo.dto.TodoDto;
import com.todo.service.TodoServiceImpl;

@RestController
@RequestMapping("api/todos")

public class TodoController {
   @Autowired
    private TodoServiceImpl todoService;

   @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
	public ResponseEntity<TodoDto> addTod(  @RequestBody TodoDto toDoDto ){
		TodoDto savedTodo = todoService.addTodo(toDoDto);

        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
	}
    
   @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/get/{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long id){
    	TodoDto todo = todoService.getTodo(id);
    	return new ResponseEntity<TodoDto>(todo, HttpStatus.OK);
    }
   @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/get")
    public ResponseEntity<List<TodoDto>> getAllTodo(){

    	List<TodoDto> allTodos = todoService.getAllTodos();
    	return new ResponseEntity<List<TodoDto>>(allTodos, HttpStatus.OK);
    }
   @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto dto,@PathVariable Long id){
    	TodoDto updateTodo = todoService.updateTodo(dto, id);
    	
		return new ResponseEntity<TodoDto>(updateTodo, HttpStatus.OK);
    	
    }
   @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id){
    	todoService.deleteTodo(id);
		return ResponseEntity.ok("user deleted is successfu;lly on id - "+id);
    	
    }
   @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo( @PathVariable Long id){
    	TodoDto completeTodo = todoService.completeTodo(id);
    	return new ResponseEntity<TodoDto>(completeTodo, HttpStatus.FOUND);
    }
   @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/completed")
    public ResponseEntity<TodoDto> incompleteTodo( @PathVariable Long id){
    	TodoDto completeTodo = todoService.inCompleteTodo(id);
    	return new ResponseEntity<TodoDto>(completeTodo, HttpStatus.FOUND);
    }
    
} 
