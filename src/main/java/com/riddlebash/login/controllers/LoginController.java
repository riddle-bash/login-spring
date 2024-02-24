package com.riddlebash.login.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
public class LoginController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	private UserService userService;
	
	private Mapper<UserEntity, UserDto> userMapper;
	
	public LoginController(UserService userService, Mapper<UserEntity, UserDto> userMapper) {
		this.userService = userService;
		this.userMapper = userMapper;
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "login_form";
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
		
		userDto.setRole("USER");
		UserEntity userEntity = userMapper.mapFrom(userDto);
		UserEntity savedUser = userService.saveUser(userEntity);
		
		model.addAttribute("message", "Registered Successfully!");
		return "register_form";
	}
	
	@GetMapping("/user-page")
	public String getUserPage(Model model, Principal principal) {
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		
		return "user_page";
	}
	
	@GetMapping("/admin-page")
	public String getAdminPage(Model model, Principal principal) {
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
	
		List<UserEntity> users = userService.findAll();
		model.addAttribute("users", users);
		
		return "admin_page";
	}
}
