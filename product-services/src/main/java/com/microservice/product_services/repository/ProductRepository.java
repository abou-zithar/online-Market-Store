package com.microservice.product_services.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.microservice.product_services.model.Product;

//this is the repository that interact with the database
public interface ProductRepository extends MongoRepository<Product, String > {
}
