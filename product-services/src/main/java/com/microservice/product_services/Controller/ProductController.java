package com.microservice.product_services.Controller;

import com.microservice.product_services.DTO.ProductRequest;
import com.microservice.product_services.DTO.ProductResponse;
import com.microservice.product_services.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){

        productService.createProduct(productRequest);


    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)

    public List<ProductResponse> getAllProduct(){

        return productService.getAllProducts();


    }


}
