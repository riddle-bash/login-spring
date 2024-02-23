package com.riddlebash.login.services;

import com.riddlebash.login.domains.entities.UserEntity;

public interface UserService {
	
	UserEntity createUser(UserEntity user);
	
}
