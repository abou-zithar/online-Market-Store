package com.microservice.product_services;

import com.microservice.product_services.DTO.ProductRequest;
import com.microservice.product_services.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mongodb.MongoDBContainer;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServicesApplicationTests {
@Container
static MongoDBContainer mongoDBContainer = new MongoDBContainer( "mongo:4.4.2");

@Autowired
private MockMvc mockMvc;
@Autowired
private ObjectMapper objectMapper;
@Autowired
private ProductRepository productRepository;
@DynamicPropertySource
static  void setProperties(DynamicPropertyRegistry dymDynamicPropertyRegistry){
	dymDynamicPropertyRegistry.add( "spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl) ;
}

	@BeforeEach
	void setUp() {
		productRepository.deleteAll(); // wipe DB before each test
	}

	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest productRequest = getProductRequest();
		String productRequestString =objectMapper.writeValueAsString(productRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequestString))
				.andExpect(status().isCreated());
        Assertions.assertEquals(1, productRepository.findAll().size());
	}

	private ProductRequest getProductRequest(){
		return ProductRequest.builder()
				.name ("iPhone 13")
				.description("iPhone 13")
				.price(BigDecimal.valueOf(1200))
						.build();
	}
}
