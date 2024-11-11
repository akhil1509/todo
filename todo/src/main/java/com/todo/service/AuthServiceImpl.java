package com.todo.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todo.dto.LoginDto;
import com.todo.dto.RegisterDto;
import com.todo.entity.Role;
import com.todo.entity.User;
import com.todo.exception.ResourceNotFoundException;
import com.todo.reposotory.RoleRepository;
import com.todo.reposotory.UserRepository;

import lombok.AllArgsConstructor;

@Service

public class AuthServiceImpl implements AuthService {
    @Autowired
	private UserRepository userRepository;
    @Autowired
	private RoleRepository roleRepository;
    @Autowired
	private PasswordEncoder passwordEncoder;
    
	@Override
	public String register(RegisterDto registerDto) {
		//check username already exixst in db
		if(userRepository.existsByUsername(registerDto.getUsername())) {
			throw new ResourceNotFoundException("already exist");
		}
		
		
		// check by email
		if(userRepository.existsByEmail(registerDto.getEmail())) {
			throw new ResourceNotFoundException("already exist");
		}
		
		User user = new User();
		user.setName(registerDto.getName());
		user.setUsername(registerDto.getUsername());
		user.setEmail(registerDto.getEmail());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		
		Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);

        user.setRoles(roles);

        userRepository.save(user);

        return "User Registered Successfully!.";
	

	
	}

}
