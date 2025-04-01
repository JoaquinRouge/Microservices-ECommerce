package com.joaquinrouge.ecommerce.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaquinrouge.ecommerce.product.model.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long>{
	Optional<List<Product>> findByBrand(String brand);
	boolean existsByNameAndBrandAndIdNot(String name,String brand,Long id);
	boolean existsByNameAndBrand(String name,String brand);
}
