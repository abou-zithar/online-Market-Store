package com.microservice.product_services.service;

import com.microservice.product_services.DTO.ProductRequest;
import com.microservice.product_services.DTO.ProductResponse;
import com.microservice.product_services.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.microservice.product_services.model.Product;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;



    public void createProduct(ProductRequest productRequest){

        Product product = null;
        try {
            product =  Product.builder()
                    .name (productRequest.getName())
                    .description(productRequest.getDescription())
                    .price(productRequest.getPrice())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (product !=null){
            productRepository.save(product);
            log.info("Product {} is saved",product.getId());
        }

    }




    public List<ProductResponse> getAllProducts(){
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();

    }

    private  ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder().
                id(product.getId()).
                name(product.getName()).
                description(product.getDescription()).price(product.getPrice())
                .build();
    }
}
