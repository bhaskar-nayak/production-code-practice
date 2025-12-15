package com.production.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.production.dto.CarDto;
import com.production.model.Car;
import com.production.service.CarService;
import com.production.util.ApiResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarController {
	private CarService carService;
	
	@PostMapping("addCar")
	public ResponseEntity<ApiResponse<CarDto>> addCar(@RequestBody CarDto carDto){
		return ResponseEntity.ok(new ApiResponse<>(201, "Car Added Succesfully!", LocalDateTime.now(), carService.createCar(carDto)));
	}
	@GetMapping
	public ResponseEntity<ApiResponse<List<CarDto>>> listCars(){
		return ResponseEntity.ok(new ApiResponse<>(200, "success", LocalDateTime.now(), carService.listAllCars()));
	}
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CarDto>> getCarById(@PathVariable Long id){
		return ResponseEntity.ok(new ApiResponse<>(200, "success", LocalDateTime.now(), carService.getCarById(id)));
	}
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<CarDto>> updateCar(@PathVariable Long id, @RequestBody CarDto carDto){
		return ResponseEntity.ok(new ApiResponse<>(200, "updated success", LocalDateTime.now(), carService.updateCar(id, carDto)));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<CarDto>> deleteCarById(@PathVariable Long id){
		return ResponseEntity.ok(new ApiResponse<>(200, "deleted success", LocalDateTime.now(), null));
	}
//	@FeignClient(name = "product-service", url = "http://localhost:8081")
//	public interface ProductServiceClient {
//
//	    @PostMapping("/orders")
//	    ResponseEntity<OrderResponseDTO> createOrder(@RequestBody @Valid OrderRequestDTO orderRequestDTO);
//
//	    @GetMapping("/orders/{id}")
//	    ResponseEntity<OrderResponseDTO> getOrder(@PathVariable("id") Long id);
//	}

}
