package com.riddlebash.login.services.impl;

import com.riddlebash.login.repositories.UserRepository;

import org.springframework.stereotype.Service;

import com.riddlebash.login.domains.entities.UserEntity;
import com.riddlebash.login.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserEntity createUser(UserEntity user) {
		return userRepository.save(user);
	}

}
