package com.joaquinrouge.ecommerce.order.service;

import java.time.LocalDate;
import java.util.List;

import com.joaquinrouge.ecommerce.order.model.Order;

public interface IOrderService {
	List<Order> getAllOrders();
	Order getById(Long id);
	List<Order> getByDate(LocalDate date);
	List<Order> getByUserId(Long userId);
	Order createOrder(Order order);
	void deleteOrder(Long id);
	Order updateOrder(Order order);
}
