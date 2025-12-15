package com.production.util;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
	private int status;
	private String message;
	private LocalDateTime timestamp;
	private T data;
}
