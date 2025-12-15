package com.production.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.production.dto.CarDto;
import com.production.exceptions.ResourceAlreadyExistedException;
import com.production.model.Car;
import com.production.repo.CarRepository;
import com.production.service.CarServiceImpl;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class CarServiceIntegrationTest {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarServiceImpl carService;

    private CarDto dto;
    private Car entity;

    @BeforeEach
    void setUp() {
        carRepository.deleteAll(); // ✅ Ensure clean DB before every test

        dto = new CarDto(null, "bmws", 900099.0);
        entity = new Car(null, "bmws", 900009.0);
    }

    @Test
    void testCarCreate_Integration() {
        // Given
        CarDto input = new CarDto(null, "bmw", 900999.9);

        // When
        CarDto result = carService.createCar(input);

        // Then
        System.out.println("saved to db: " + result);
        assertNotNull(result.getId());
        assertEquals("bmw", result.getName());

        Optional<Car> carFromDb = carRepository.findByName("bmw");
        System.out.println("retrieve from db: " + carFromDb);
        assertTrue(carFromDb.isPresent());
    }

    @Test
    void testCarAlreadyExisted_ThrowException() {
        // Given
        carRepository.save(entity); //  Save to simulate existing car
        Optional<Car> fromDb = carRepository.findByName("bmw");
        System.out.println("Car found in DB before creating: " + fromDb);
        // When & Then
        ResourceAlreadyExistedException exception = assertThrows(
            ResourceAlreadyExistedException.class,
            () -> carService.createCar(dto)
        );

        assertTrue(exception.getMessage().toLowerCase().contains("already existed"));
    }
}

