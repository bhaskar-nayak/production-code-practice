package com.production.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.production.dto.HomeRequestDto;
import com.production.dto.HomeResponseDto;
import com.production.service.HomeService;
import com.production.util.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("v1/home")
@Slf4j
@RequiredArgsConstructor
public class HomeController {
	private final HomeService homeService;
	@PostMapping
	public ResponseEntity<ApiResponse<HomeResponseDto>> addHome(@RequestBody HomeRequestDto homeRequestDto){
		log.info("received request to create Home");
		HomeResponseDto homeResponse= homeService.createHome(homeRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse<>(201, "success", LocalDateTime.now(), homeResponse));
	}
}
