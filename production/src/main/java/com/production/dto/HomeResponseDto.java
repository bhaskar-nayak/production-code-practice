package com.production.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HomeResponseDto {
	private Long id;	
	private String name;	
	private String loacation;
	
}
//package com.micro.product_service.client;
//
//import com.micro.product_service.dto.InventoryResponseDTO;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@FeignClient(
//        name = "inventory-service",
//        url = "${inventory.service.url}",
//        fallback = InventoryClientFallback.class
//)
//public interface InventoryClient {
//
//    @GetMapping("/api/inventory/check")
//    InventoryResponseDTO checkStock(@RequestParam("productId") Long productId);
//}
//package com.micro.product_service.client;
//
//import com.micro.product_service.dto.InventoryResponseDTO;
//import org.springframework.stereotype.Component;
//
//@Component
//public class InventoryClientFallback implements InventoryClient {
//
//    @Override
//    public InventoryResponseDTO checkStock(Long productId) {
//        // graceful fallback response
//        return new InventoryResponseDTO(productId, false, "Inventory service unavailable");
//    }
//}
//package com.micro.product_service.config;
//
//import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import java.time.Duration;
//
//@Configuration
//public class Resilience4jConfig {
//
//    @Bean
//    public io.github.resilience4j.circuitbreaker.CircuitBreakerConfig customCBConfig() {
//        return CircuitBreakerConfig.custom()
//                .failureRateThreshold(50)
//                .waitDurationInOpenState(Duration.ofSeconds(10))
//                .slidingWindowSize(10)
//                .build();
//    }
//}
//resilience4j:
//	  circuitbreaker:
//	    instances:
//	      inventoryServiceCB:
//	        registerHealthIndicator: true
//	        slidingWindowSize: 10
//	        minimumNumberOfCalls: 5
//	        waitDurationInOpenState: 10s
//	        failureRateThreshold: 50
//	        eventConsumerBufferSize: 10
//	  retry:
//	    instances:
//	      inventoryServiceRetry:
//	        maxAttempts: 3
//	        waitDuration: 500ms
//package com.micro.product_service.service;
//
//import com.micro.product_service.client.InventoryClient;
//import com.micro.product_service.dto.InventoryResponseDTO;
//import com.micro.product_service.dto.ProductRequestDTO;
//import com.micro.product_service.dto.ProductResponseDTO;
//import com.micro.product_service.exception.ResourceNotFoundException;
//import com.micro.product_service.mapper.ProductMapper;
//import com.micro.product_service.model.Product;
//import com.micro.product_service.repository.ProductRepository;
//
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import io.github.resilience4j.retry.annotation.Retry;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class ProductServiceImpl implements ProductService {
//
//    private static final String CB_NAME = "inventoryServiceCB";
//    private static final String RETRY_NAME = "inventoryServiceRetry";
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private ProductMapper productMapper;
//
//    @Autowired
//    private InventoryClient inventoryClient;
//
//    @Override
//    public ProductResponseDTO addProduct(ProductRequestDTO dto) {
//        Product product = productMapper.toEntity(dto);
//        return productMapper.toDto(productRepository.save(product));
//    }
//
//    @Override
//    public List<ProductResponseDTO> listProducts() {
//        return productRepository.findAll()
//                .stream()
//                .map(productMapper::toDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    @CircuitBreaker(name = CB_NAME, fallbackMethod = "inventoryFallback")
//    @Retry(name = RETRY_NAME)
//    public ProductResponseDTO getProduct(Long id) {
//
//        Product product = productRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
//
//        ProductResponseDTO dto = productMapper.toDto(product);
//
//        // Call Feign Client
//        InventoryResponseDTO inventory = inventoryClient.checkStock(id);
//
//        dto.setInStock(inventory.isInStock());
//        dto.setInventoryMessage(inventory.getMessage());
//
//        return dto;
//    }
//
//    // fallback
//    public ProductResponseDTO inventoryFallback(Long id, Throwable throwable) {
//
//        ProductResponseDTO dto = productMapper.toDto(
//                productRepository.findById(id)
//                        .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id)));
//
//        dto.setInStock(false);
//        dto.setInventoryMessage("Inventory service unavailable (fallback)");
//
//        return dto;
//    }
//
//    @Override
//    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto) {
//        Product product = productRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
//
//        product.setName(dto.getName());
//        product.setCategory(dto.getCategory());
//        product.setPrice(dto.getPrice());
//
//        return productMapper.toDto(productRepository.save(product));
//    }
//
//    @Override
//    public void deleteProduct(Long id) {
//        Product product = productRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
//        productRepository.delete(product);
//    }
//}
