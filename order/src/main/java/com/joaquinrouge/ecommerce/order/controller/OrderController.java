package com.joaquinrouge.ecommerce.order.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaquinrouge.ecommerce.order.model.Order;
import com.joaquinrouge.ecommerce.order.service.IOrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private IOrderService orderService;
	
	@GetMapping()
	public ResponseEntity<Object> getAllOrders(){
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getById(@PathVariable("id") Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(orderService.getById(id));
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/date/{date}")
	public ResponseEntity<Object> getByDate(@PathVariable("date") LocalDate date){
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getByDate(date));
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<Object> getByDate(@PathVariable("userId") Long userId){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(orderService.getByUserId(userId));
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<Object> createOrder(@RequestBody Order order){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(order));
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/delete/{deleteId}")
	public ResponseEntity<Object> deleteById(@PathVariable("deleteId") Long deleteId){
		try {
			orderService.deleteOrder(deleteId);
			return ResponseEntity.status(HttpStatus.OK).build();
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> updateOrder(@RequestBody Order order){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(orderService.updateOrder(order));
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
