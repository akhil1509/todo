package com.todo.mapper.bean;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Modelmapper {
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
