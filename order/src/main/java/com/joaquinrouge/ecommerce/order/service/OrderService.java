package com.joaquinrouge.ecommerce.order.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaquinrouge.ecommerce.order.model.Order;
import com.joaquinrouge.ecommerce.order.model.OrderProduct;
import com.joaquinrouge.ecommerce.order.repository.IOrderRepository;

@Service
public class OrderService implements IOrderService {

	@Autowired
	private IOrderRepository orderRepo;
	
	@Override
	public List<Order> getAllOrders() {
		return orderRepo.findAll();
	}

	@Override
	public Order getById(Long id) {
		return orderRepo.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Order not found"));
	}

	@Override
	public List<Order> getByDate(LocalDate date) {
	    LocalDateTime startOfDay = date.atStartOfDay(); // 2025-04-01T00:00:00
	    LocalDateTime endOfDay = date.atTime(LocalTime.MAX); // 2025-04-01T23:59:59

	    return orderRepo.findAllByDateBetween(startOfDay, endOfDay);
	}

	@Override
	public List<Order> getByUserId(Long userId) {
		return orderRepo.findByUserId(userId)
				.orElseThrow(()-> new IllegalArgumentException("No orders for user id: " + userId));
	}

	@Override
	public Order createOrder(Order order) {
		
		double total = 0;
		
		for (OrderProduct o : order.getProductList()) {
			total += o.getQuantity() * o.getPrice();
		}
		
		order.setTotal(total);
		
		return orderRepo.save(order);
	}

	@Override
	public void deleteOrder(Long id) {
		if(!orderRepo.existsById(id)) {
			throw new IllegalArgumentException("Order not found");
		}
		
		orderRepo.deleteById(id);
	}

	@Override
	public Order updateOrder(Order order) {
		
		Order orderFromDb = this.getById(order.getId());
		
		orderFromDb.setProductList(order.getProductList());
		
		return orderRepo.save(orderFromDb);
	}

}
