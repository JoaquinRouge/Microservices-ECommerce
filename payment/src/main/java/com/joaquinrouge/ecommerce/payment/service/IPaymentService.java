package com.joaquinrouge.ecommerce.payment.service;

import com.joaquinrouge.ecommerce.payment.model.Payment;

public interface IPaymentService {
	Payment pay(Long orderId);
}
