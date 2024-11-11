package com.todo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandeller {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handelExceptionOnGlobal(ResourceNotFoundException ex){
		
		Map map = new HashMap();
		map.put("message", ex.getMessage());
		map.put("success", false);
		map.put("status",HttpStatus.NOT_FOUND );
		
		return new ResponseEntity<>(map, HttpStatus.OK);
		
	}

}
