package com.joaquinrouge.ecommerce.order.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaquinrouge.ecommerce.order.model.Order;

import feign.Param;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long>{

	Optional<List<Order>> findByUserId(Long userId);
	
	List<Order> findAllByDateBetween(@Param("startOfDay") LocalDateTime startOfDay, 
	                                      @Param("endOfDay") LocalDateTime endOfDay);
}
