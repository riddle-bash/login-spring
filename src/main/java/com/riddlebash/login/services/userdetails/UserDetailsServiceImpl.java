package com.riddlebash.login.services.userdetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.riddlebash.login.domains.entities.UserEntity;
import com.riddlebash.login.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity userEniEntity = userRepository.findByUsername(username);
		
		if (userEniEntity == null) {
			throw new UsernameNotFoundException("Username does not exist!");
		}

		return new UserDetailsImpl(userEniEntity);
	}

}
