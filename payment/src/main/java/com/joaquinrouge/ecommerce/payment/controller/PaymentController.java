package com.joaquinrouge.ecommerce.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaquinrouge.ecommerce.payment.model.Payment;
import com.joaquinrouge.ecommerce.payment.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/{orderId}")
    public ResponseEntity<Object> realizarPago(@PathVariable("orderId") Long orderId) {
    	try {
    		Payment pago = paymentService.pay(orderId);
    		return ResponseEntity.ok(pago);    		
    	}catch(IllegalArgumentException e) {
    		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
    	}catch(IllegalStateException e) {
    		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
    	}
    }
	
}
