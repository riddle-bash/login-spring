package com.riddlebash.login.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.riddlebash.login.domains.dtos.UserDto;
import com.riddlebash.login.domains.entities.UserEntity;
import com.riddlebash.login.mappers.Mapper;

@Component
public class UserMapperImpl implements Mapper<UserEntity, UserDto> {
	
	private ModelMapper modelMapper;

	public UserMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public UserDto mapTo(UserEntity a) {
		return modelMapper.map(a, UserDto.class);
	}

	@Override
	public UserEntity mapFrom(UserDto b) {
		return modelMapper.map(b, UserEntity.class);
	}

}
