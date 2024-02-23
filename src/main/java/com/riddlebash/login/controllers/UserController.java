package com.riddlebash.login.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.riddlebash.login.domains.dtos.UserDto;
import com.riddlebash.login.domains.entities.UserEntity;
import com.riddlebash.login.mappers.Mapper;
import com.riddlebash.login.services.UserService;

@Controller
public class UserController {
	
	private UserService userService;
	
	private Mapper<UserEntity, UserDto> userMapper;
	
	public UserController(UserService userService, Mapper<UserEntity, UserDto> userMapper) {
		this.userService = userService;
		this.userMapper = userMapper;
	}

	@GetMapping("/registration")
	public String getRegistration(Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("userDto", userDto);
		return "register_form";
	}
	
	@PostMapping("/registration")
	public String saveUser(
			@ModelAttribute("userDto") UserDto userDto,
			Model model) {
		UserEntity userEntity = userMapper.mapFrom(userDto);
		UserEntity savedUser = userService.createUser(userEntity);
		model.addAttribute("message", "Registered Successfully!");
		return "register_form";
	}

}
