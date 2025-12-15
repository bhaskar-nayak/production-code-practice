package com.production.service;

import java.util.List;

import com.production.dto.HomeRequestDto;
import com.production.dto.HomeResponseDto;

public interface HomeService {
	public HomeResponseDto createHome(HomeRequestDto homeRequestDto);
	public List<HomeResponseDto> list();
	public HomeResponseDto getHomeById(Long id);
	public HomeResponseDto updateHome(Long id, HomeRequestDto homeRequestDto);
	public HomeResponseDto deleteHome(Long id);
	
}
