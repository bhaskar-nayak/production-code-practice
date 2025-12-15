package com.production.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HomeRequestDto {
	private Long id;
	@NotBlank(message = "Home name cannot be blank")
	@Size(min = 2, message = "home name must be between 2 to 1000 characters")
    private String name;
	@NotNull(message = "location is required")
	private String loacation;
	@NotNull(message = "Total amount is required")
	@Min(value = 2, message = "Total amount must greater than equal to 2")
	@Max(value = 10000000, message = "Total amount must not excced to 10000000")
	private double amount;
}
