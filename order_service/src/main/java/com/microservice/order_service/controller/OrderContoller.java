package com.microservice.order_service.controller;

import com.microservice.order_service.dto.OrderDto;
import com.microservice.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderContoller {

    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderDto orderRequestDto){

        orderService.placeOrder(orderRequestDto);

        return "the order is created succefully ";

    }

}
