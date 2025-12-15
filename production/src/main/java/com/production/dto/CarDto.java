package com.production.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarDto {
	private Long id;	
	private String name;	
	private double price;
}
