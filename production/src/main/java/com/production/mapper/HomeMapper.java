package com.production.mapper;

import org.mapstruct.Mapper;

import com.production.dto.HomeRequestDto;
import com.production.dto.HomeResponseDto;
import com.production.model.Home;

@Mapper(componentModel = "spring")
public interface HomeMapper {
//	@Mapping(target ='id', ignore = true)
	Home mapToEntity(HomeRequestDto homeRequestDto);
	HomeResponseDto mapToDto(Home home);
	
}
