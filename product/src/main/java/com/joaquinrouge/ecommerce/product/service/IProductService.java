package com.joaquinrouge.ecommerce.product.service;

import java.util.List;

import com.joaquinrouge.ecommerce.product.model.Product;

public interface IProductService {
	List<Product> getAllProducts();
	Product getProductById(Long id);
	List<Product> getProductsByBrand(String brand);
	Product createProduct(Product product);
	void deleteProduct(Long id);
	Product updateProduct(Product product);
	
}
