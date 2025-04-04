package com.joaquinrouge.ecommerce.payment.dto;

public class OrderDto {
	private Long id;
	private Long userId;
	private double total;
	
	public OrderDto() {
		
	}

	public OrderDto(Long id, Long userId, double total) {
		super();
		this.id = id;
		this.userId = userId;
		this.total = total;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
