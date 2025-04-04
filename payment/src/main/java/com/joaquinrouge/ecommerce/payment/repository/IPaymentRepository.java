package com.joaquinrouge.ecommerce.payment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaquinrouge.ecommerce.payment.model.Payment;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Long>{
	 Optional<Payment> findByOrderId(Long orderId);
}
