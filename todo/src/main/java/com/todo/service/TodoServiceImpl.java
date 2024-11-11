package com.todo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.dto.TodoDto;
import com.todo.entity.Todo;
import com.todo.exception.ResourceNotFoundException;
import com.todo.reposotory.TodoRepository;



@Service

public class TodoServiceImpl  implements TodoService{

	@Autowired
	public TodoRepository todoRepository;
	@Autowired
	public ModelMapper modelMapper;
	
	@Override
	public TodoDto addTodo(TodoDto todoDto) {
		Todo todo = modelMapper.map(todoDto, Todo.class);
		Todo saved = todoRepository.save(todo);
		
		TodoDto dto = modelMapper.map(saved, TodoDto.class);
		return dto;
		
	}
	

	@Override
	public TodoDto getTodo(Long id) {
		Todo gettodo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id is not available:"+id));
		return modelMapper.map(gettodo, TodoDto.class);
	}
	

	@Override
	public List<TodoDto> getAllTodos() {
		List<Todo> findAllTodo = todoRepository.findAll();
		
		return findAllTodo.stream().map(e -> modelMapper.map(e, TodoDto.class)).collect(Collectors.toList());
	}
	

	@Override
	public TodoDto updateTodo(TodoDto todoDto, Long id) {
		Todo gettodo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id is not available:"+id));
		gettodo.setId(todoDto.getId());
		gettodo.setTitle(todoDto.getTitle());
		gettodo.setDescription(todoDto.getDescription());
		gettodo.setCompleted(todoDto.isCompleted());
		Todo saveTodo = todoRepository.save(gettodo);
		return modelMapper.map(todoDto, TodoDto.class);
	}
	

	@Override
	public void deleteTodo(Long id) {
		Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id is not available: "+id));
		todoRepository.deleteById(id);
	}

	
	@Override
	public TodoDto completeTodo(Long id) {
		Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id is not available:"+id));
		todo.setCompleted(Boolean.TRUE);
		Todo saveTodo = todoRepository.save(todo);
		return modelMapper.map(saveTodo, TodoDto.class);
	}


	@Override
	public TodoDto inCompleteTodo(Long id) {
		Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id is not available:"+id));
		todo.setCompleted(Boolean.FALSE);
		Todo save = todoRepository.save(todo);
		
		return  modelMapper.map(save, TodoDto.class);
	}

}
