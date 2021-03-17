package com.infosys.project.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.infosys.project.product.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByCategoryContainingAndProductNameContaining(String category, String productName);

	List<Product> findByProductNameContaining(String productName);

	List<Product> findByCategoryContaining(String category);

	void deleteBySellerId(Long sellerId);

	List<Product> findBySellerId(Long sellerId);

	Optional<Product> findByProdId(Long prodId);

}
