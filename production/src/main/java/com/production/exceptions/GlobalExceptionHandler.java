package com.production.exceptions;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.production.util.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceAlreadyExistedException.class)	
	public ResponseEntity<ApiResponse<String>> handlerResourceAlreadyExisted(ResourceAlreadyExistedException ex){
		log.warn("Resource Already Existed Exception: {}", ex.getMessage());
		return ResponseEntity.ok(new ApiResponse<>(404, "ResourceAlreadyExisted:" + ex.getMessage(), LocalDateTime.now(), null));
	}
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
      Map<String, String> errors = ex.getBindingResult()
              .getFieldErrors()
              .stream()
              .collect(Collectors.toMap(
                      FieldError::getField,
                      FieldError::getDefaultMessage,
                      (existing, replacement) -> existing // if duplicate field errors
              ));

      log.warn("Validation failed: {}", errors);

      ApiResponse<Map<String, String>> response = new ApiResponse<>(
              400,
              "Validation Failed",
              LocalDateTime.now(),
              errors
      );

      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
	@ExceptionHandler(CarNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> handlerCarNotFound(CarNotFoundException ex){
		log.warn("Car Not Found Exception: {}", ex.getMessage());
		return ResponseEntity.ok(new ApiResponse<>(404, "CarNotFound:" + ex.getMessage(), LocalDateTime.now(), null));
	}
	@ExceptionHandler(HomeNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handlerHomeNotFound(HomeNotFoundException ex){
		log.warn("Home not found exception :{}", ex.getMessage());
		return ResponseEntity.ok(new ApiResponse<>(404, "Home not found exception:"+ ex.getMessage(), LocalDateTime.now(), null));
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<String>> globalHandler(Exception ex){
		log.error("unhandled Exception Occured: {}", ex.getMessage(), ex);
		return ResponseEntity.ok(new ApiResponse<>(500, "Internal_Server_Error:" + HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now(), null));
	}
}
