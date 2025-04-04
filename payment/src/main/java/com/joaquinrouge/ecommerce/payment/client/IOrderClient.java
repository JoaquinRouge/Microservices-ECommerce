package com.joaquinrouge.ecommerce.payment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.joaquinrouge.ecommerce.payment.dto.OrderDto;

@FeignClient(name = "ORDER-SERVICE",url = "http://localhost:9000")
public interface IOrderClient {
    @GetMapping("/order/{id}")
    OrderDto getOrder(@PathVariable("id") Long orderId);
}
