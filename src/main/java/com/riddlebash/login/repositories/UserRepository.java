package com.riddlebash.login.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.riddlebash.login.domains.entities.UserEntity;
import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>, PagingAndSortingRepository<UserEntity, Long> {
	
	UserEntity findByUsername(String username);

}
