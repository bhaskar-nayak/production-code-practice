package com.production.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.production.dto.CarDto;
import com.production.exceptions.CarNotFoundException;
import com.production.exceptions.ResourceAlreadyExistedException;
import com.production.mapper.CarMapper;
import com.production.model.Car;
import com.production.repo.CarRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService{

	private CarRepository carRepository;
	private CarMapper carMapper;
	
	@Transactional
	@Override
	public CarDto createCar(CarDto carDto) {
		Optional<Car> carByName =carRepository.findByName(carDto.getName());
		if(carByName.isPresent()) {
			throw new ResourceAlreadyExistedException("Car" + carDto.getName() + " Already Existed!");
		}
	  Car car = carMapper.mapToEntity(carDto);
		return carMapper.mapToDto(carRepository.save(car));
	}

	@Override
	public List<CarDto> listAllCars() {		
		return carRepository.findAll().stream()
				.map(carMapper::mapToDto)
				.collect(Collectors.toList());
	}

	@Override
	public CarDto getCarById(Long id) {
		Car car= carRepository.findById(id)
		.orElseThrow(()-> new CarNotFoundException("Car is not found with id:" + id));
		return carMapper.mapToDto(car);
	}

	@Override
	public CarDto updateCar(Long id, CarDto carDto) {
		Car car = carRepository.findById(id)
				.orElseThrow(()-> new CarNotFoundException("car is not found with id:" +id));
		car.setName(carDto.getName());
		car.setPrice(carDto.getPrice());
		return carMapper.mapToDto(carRepository.save(car));
	}

	@Override
	public void deleteCar(Long id) {
		Car car = carRepository.findById(id)
				.orElseThrow(()-> new CarNotFoundException("car is not found with id: "+ id));
		carRepository.delete(car);
		
	}

}
