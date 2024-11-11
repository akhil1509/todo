package com.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.dto.LoginDto;
import com.todo.dto.RegisterDto;
import com.todo.service.AuthServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
	@Autowired
	private AuthServiceImpl authServiceImpl;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
		String register = authServiceImpl.register(registerDto);
		return new ResponseEntity<String>(register, HttpStatus.CREATED);
		
	}
	
//	@PostMapping("/login")
//	public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
//		String login = authServiceImpl.login(loginDto);
//		return new ResponseEntity<String>(login, HttpStatus.OK);
//	}

}
