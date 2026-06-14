package br.com.orderservice.controller;


import br.com.orderservice.dto.OrderRequest;
import br.com.orderservice.enums.OrderStatus;
import br.com.orderservice.model.Order;
import br.com.orderservice.producer.OrderProducer;
import br.com.orderservice.repository.OrderRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;

    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderRequest request) {
        Order order = Order.builder()
                .clientId(request.clientId())
                .totalValue(request.totalValue())
                .status(OrderStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        Order savedOrder = orderRepository.save(order);
        orderProducer.sendOrderEvent(savedOrder);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(savedOrder);
    }
}
