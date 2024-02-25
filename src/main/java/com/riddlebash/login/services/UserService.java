package com.riddlebash.login.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.riddlebash.login.domains.entities.UserEntity;

public interface UserService {
	
	UserEntity saveUser(UserEntity user);

	Page<UserEntity> findAll(Pageable pageable);

	Optional<UserEntity> findById(Long id);

	void deleteUser(Long id);

	boolean existById(Long id);
	
}
