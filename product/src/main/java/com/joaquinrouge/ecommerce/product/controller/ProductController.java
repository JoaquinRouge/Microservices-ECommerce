package com.joaquinrouge.ecommerce.product.controller;

import java.util.List;

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

import com.joaquinrouge.ecommerce.product.model.Product;
import com.joaquinrouge.ecommerce.product.service.IProductService;
import com.joaquinrouge.ecommerce.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductService prodService;
	
	@GetMapping()
	public ResponseEntity<Object> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(prodService.getAllProducts());
	}
	
	@GetMapping("/brand/{brand}")
	public ResponseEntity<Object> getProductByBrand(@PathVariable("brand") String brand){
		try {
			List<Product> list = prodService.getProductsByBrand(brand);
			return ResponseEntity.status(HttpStatus.OK).body(list);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<Object> createProduct(@RequestBody Product product){
		try {
			Product createProduct = prodService.createProduct(product);
			return ResponseEntity.status(HttpStatus.CREATED).body(product);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/delete/{deleteId}")
	public ResponseEntity<Object> deleteProduct(@PathVariable("deleteId") Long deleteId){
		try {
			prodService.deleteProduct(deleteId);
			return ResponseEntity.status(HttpStatus.OK).build();
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<Object> updateProduct(@RequestBody Product product){
		try {
			Product updateProduct = prodService.updateProduct(product);
			return ResponseEntity.status(HttpStatus.CREATED).body(updateProduct);
		}catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
}
