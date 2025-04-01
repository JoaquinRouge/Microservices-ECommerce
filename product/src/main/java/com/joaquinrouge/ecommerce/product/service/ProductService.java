package com.joaquinrouge.ecommerce.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaquinrouge.ecommerce.product.model.Product;
import com.joaquinrouge.ecommerce.product.repository.IProductRepository;

@Service
public class ProductService implements IProductService {

	@Autowired
	private IProductRepository prodRepo;
	
	@Override
	public List<Product> getAllProducts() {
		return prodRepo.findAll();
	}

	@Override
	public Product getProductById(Long id) {
		return prodRepo.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Product not found"));
	}

	@Override
	public List<Product> getProductsByBrand(String brand) {
		return prodRepo.findByBrand(brand)
				.orElseThrow(()-> new IllegalArgumentException("Product not found"));
	}

	@Override
	public Product createProduct(Product product) {
		
		if(prodRepo.existsByNameAndBrand(product.getName(),
				product.getBrand())) {
			throw new IllegalArgumentException("Product already exists");
		}
		
		return prodRepo.save(product);
	}

	@Override
	public void deleteProduct(Long id) {
		if(!prodRepo.existsById(id)) {
			throw new IllegalArgumentException("Product not found");
		}
		prodRepo.deleteById(id);
	}

	@Override
	public Product updateProduct(Product product) {
	
		Product productFromDb = this.getProductById(product.getId());
		
		if(prodRepo.existsByNameAndBrandAndIdNot(product.getName(), product.getBrand(), product.getId())) {
			throw new IllegalArgumentException("Product already exists");
		}
		
		productFromDb.setBrand(product.getBrand());
		productFromDb.setDescription(product.getDescription());
		productFromDb.setImageUrl(product.getImageUrl());
		productFromDb.setName(product.getName());
		productFromDb.setPrice(product.getPrice());
		
		return prodRepo.save(productFromDb);
	}

}
