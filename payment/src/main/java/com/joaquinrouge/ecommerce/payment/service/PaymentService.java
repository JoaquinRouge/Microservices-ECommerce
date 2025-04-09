package com.joaquinrouge.ecommerce.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaquinrouge.ecommerce.payment.client.IOrderClient;
import com.joaquinrouge.ecommerce.payment.dto.OrderDto;
import com.joaquinrouge.ecommerce.payment.model.Payment;
import com.joaquinrouge.ecommerce.payment.model.PaymentStatus;
import com.joaquinrouge.ecommerce.payment.repository.IPaymentRepository;

import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class PaymentService implements IPaymentService {

	@Autowired
	private IPaymentRepository paymentRepository;
	
	@Autowired
	private IOrderClient orderClient;
	@Override
	public Payment pay(Long orderId) {
	    paymentRepository.findByOrderId(orderId).ifPresent(p -> {
	        throw new IllegalStateException("This order was already paid.");
	    });

	    return processPayment(orderId);
	}

	@CircuitBreaker(name = "order-service", fallbackMethod = "orderServiceFallback")
	private Payment processPayment(Long orderId) {
	    try {
	        OrderDto order = orderClient.getOrder(orderId);
	        PaymentStatus status = Math.random() > 0.2 ? PaymentStatus.COMPLETED : PaymentStatus.FAILED;
	        Payment payment = new Payment();
	        payment.setOrderId(orderId);
	        payment.setAmount(order.getTotal());
	        payment.setStatus(status);
	        return paymentRepository.save(payment);
	    } catch (FeignException e) {
	        throw new IllegalArgumentException("Order not found");
	    }
	}

	public Payment orderServiceFallback(Long orderId, Throwable t) {
	    System.out.println("Order fallback activated: " + t.getClass() + " - " + t.getMessage());
	    return new Payment(orderId, -1L, PaymentStatus.FAILED, -1);
	}

}
