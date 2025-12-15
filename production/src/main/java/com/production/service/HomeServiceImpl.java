package com.production.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.production.dto.HomeRequestDto;
import com.production.dto.HomeResponseDto;
import com.production.exceptions.HomeNotFoundException;
import com.production.mapper.HomeMapper;
import com.production.model.Home;
import com.production.repo.HomeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService{
	private final HomeRepository homeRepository;
	private final HomeMapper homeMapper;
	
	@Override
	public HomeResponseDto createHome(HomeRequestDto homeRequestDto) {
		log.info("received request for create home with homeId={}", homeRequestDto.getId());
		try {
			Home home= homeMapper.mapToEntity(homeRequestDto);
			 log.debug("Mapped Order entity: {}", home);
			Home homeSaved= homeRepository.save(home);
			log.info("Home is succeffully saved with homeId={}", homeSaved.getId());
			return homeMapper.mapToDto(homeSaved);
		}catch(Exception ex) {
			log.error("Failed to create home with homeId={}.Error{}",homeRequestDto.getId(), ex.getMessage());
			throw new HomeNotFoundException("unable to create home:"+ ex.getMessage());
		}
	}
	@Override
	public List<HomeResponseDto> list() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HomeResponseDto getHomeById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HomeResponseDto updateHome(Long id, HomeRequestDto homeRequestDto) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HomeResponseDto deleteHome(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
