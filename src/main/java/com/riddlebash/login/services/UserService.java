package com.riddlebash.login.services;

import java.util.List;
import java.util.Optional;

import com.riddlebash.login.domains.entities.UserEntity;

public interface UserService {
	
	UserEntity saveUser(UserEntity user);

	List<UserEntity> findAll();

	Optional<UserEntity> findById(Long id);

	void deleteUser(Long id);

	boolean existById(Long id);
	
}
