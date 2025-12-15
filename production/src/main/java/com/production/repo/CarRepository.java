package com.production.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.production.model.Car;

public interface CarRepository extends JpaRepository<Car, Long>{
	Optional<Car> findByName(String name);
}
