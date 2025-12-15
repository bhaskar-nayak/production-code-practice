package com.production.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private double price;
}
//com.micro.product_service
//├── client
//│     └── InventoryClient.java
//├── config
//│     └── Resilience4jConfig.java
//├── controller
//│     └── ProductController.java
//├── dto
//│     ├── ProductRequestDTO.java
//│     ├── ProductResponseDTO.java
//│     └── InventoryResponseDTO.java
//├── exception
//│     ├── GlobalExceptionHandler.java
//│     └── ResourceNotFoundException.java
//├── mapper
//│     └── ProductMapper.java
//├── model
//│     └── Product.java
//├── repository
//│     └── ProductRepository.java
//├── service
//│     ├── ProductService.java
//│     └── ProductServiceImpl.java
//└── ProductServiceApplication.java
