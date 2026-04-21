package com.microservice.order_service.service;

import com.microservice.order_service.dto.OrderDto;
import com.microservice.order_service.dto.OrderLineItemDto;
import com.microservice.order_service.model.Order;
import com.microservice.order_service.model.OrderLineItems;
import com.microservice.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
private final OrderRepository orderRepository;
    public void placeOrder(OrderDto orderRequestDto){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList = orderRequestDto.getOrderLineItemsList().
                stream().
                map(this::mapToDto).
                toList();


        order.setOrderLineItems(orderLineItemsList);


        orderRepository.save(order);

    }

    private OrderLineItems mapToDto(OrderLineItemDto orderLineItemsDto){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;

    }
}
