package com.production.service;

import java.util.List;

import com.production.dto.CarDto;

public interface CarService {
	CarDto createCar(CarDto carDto);
	List<CarDto> listAllCars();
	CarDto getCarById(Long id);
	CarDto updateCar(Long id, CarDto carDto);
	void deleteCar(Long id);
}
