package com.joaquinrouge.ecommerce.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaquinrouge.ecommerce.payment.client.IOrderClient;
import com.joaquinrouge.ecommerce.payment.dto.OrderDto;
import com.joaquinrouge.ecommerce.payment.model.Payment;
import com.joaquinrouge.ecommerce.payment.model.PaymentStatus;
import com.joaquinrouge.ecommerce.payment.repository.IPaymentRepository;

@Service
public class PaymentService implements IPaymentService {

	@Autowired
	private IPaymentRepository paymentRepository;
	
	@Autowired
	private IOrderClient orderClient;
	
	@Override
	public Payment pay(Long orderId) {
	
        paymentRepository.findByOrderId(orderId).ifPresent(p -> {
            throw new IllegalStateException("Este pedido ya fue pagado.");
        });
		
        OrderDto order = orderClient.getOrder(orderId);

        if (order == null) {
            throw new IllegalArgumentException("Pedido no encontrado.");
        }
        
        PaymentStatus status = Math.random() > 0.2 ? PaymentStatus.COMPLETED : PaymentStatus.FAILED;

        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setAmount(order.getTotal());
        payment.setStatus(status);

        return paymentRepository.save(payment);
	}

}
