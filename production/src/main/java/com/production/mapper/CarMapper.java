package com.production.mapper;

import org.mapstruct.Mapper;

import com.production.dto.CarDto;
import com.production.model.Car;

@Mapper(componentModel = "spring")
public interface CarMapper {
	Car mapToEntity(CarDto carDto);
	CarDto mapToDto(Car car);
}
