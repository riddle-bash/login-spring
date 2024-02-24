package com.riddlebash.login.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.riddlebash.login.domains.dtos.UserDto;
import com.riddlebash.login.domains.entities.UserEntity;
import com.riddlebash.login.mappers.Mapper;
import com.riddlebash.login.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;
	
	private Mapper<UserEntity, UserDto> userMapper;

	public UserController(UserService userService, Mapper<UserEntity, UserDto> userMapper) {
		this.userService = userService;
		this.userMapper = userMapper;
	}

	@GetMapping("/edit")
	public String getEditUserPage(Model model, @RequestParam Long id) {
		try {
			UserEntity user = userService.findById(id).get();
			UserDto userDto = userMapper.mapTo(user);
			model.addAttribute("userDto", userDto);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "redirect:/admin-page";
		}
		
		return "user_edit";
	}
	
	@PostMapping("/edit")
	public String postEditUser(
			@RequestParam Long id,
			@ModelAttribute UserDto userDto, 
			BindingResult result) {
		
		if (!userService.existById(id) || result.hasErrors()) {
			return "redirect:/admin-page";
		}
		
		userDto.setId(id);
		UserEntity user = userMapper.mapFrom(userDto);
		UserEntity savedUser = userService.saveUser(user);
		
		return "redirect:/admin-page";
	}
	
	@GetMapping("/delete")
	public String deleteUser(@RequestParam Long id) {
		try {
			userService.deleteUser(id);
			
		} catch (Exception e) {
			return "redirect:/admin-page";
		}
		return "redirect:/admin-page";
	}

}
