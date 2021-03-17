package com.infosys.project.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.project.product.dto.ProductDTO;
import com.infosys.project.product.entity.Product;
import com.infosys.project.product.repository.ProductRepository;
import com.infosys.project.product.validator.ProductValidator;

@Service
@Transactional
public class ProductService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductRepository productRepository;

	// get all product details
	public List<ProductDTO> getAllProducts() {
		logger.info("Product Details of all products");
		List<Product> products = productRepository.findAll();
		List<ProductDTO> productDTOs = new ArrayList<>();
		for (Product product : products) {
			ProductDTO productDTO = ProductDTO.valueOf(product);
			productDTOs.add(productDTO);
		}
		return productDTOs;
	}

	// search by category
	public List<ProductDTO> searchProductsByCategory(String category) {
		logger.info("Product Details of all products by category {}", category);
		List<Product> products = productRepository.findByCategoryContaining(category);
		System.out.println(products);
		List<ProductDTO> productDTOs = new ArrayList<>();
		for (Product product : products) {
			ProductDTO productDTO = ProductDTO.valueOf(product);
			productDTOs.add(productDTO);
		}
		return productDTOs;
	}
	
	// search by product name
	public List<ProductDTO> searchProductsByProductName(String productName) {
		logger.info("Product Details of all products by productName {}", productName);
		List<Product> products = productRepository.findByProductNameContaining(productName);
		List<ProductDTO> productDTOs = new ArrayList<>();
		for (Product product : products) {
			ProductDTO productDTO = ProductDTO.valueOf(product);
			productDTOs.add(productDTO);
		}
		return productDTOs;
	}
	
	// add product
	public String addProduct(ProductDTO productDTO) {
		logger.info("Add request for product {}", productDTO);
		String message = ProductValidator.validateProduct(productDTO);
		if (message.equalsIgnoreCase("ok")) {
			Product product = productDTO.createEntity();
			productRepository.save(product);
			return "Product added successfully";
		}
		return message;
	}

	// delete product
	public Boolean deleteProduct(Long prodId) {
		logger.info("Delete request for product with productId {}", prodId);
		Optional<Product> optionalProduct = productRepository.findById(prodId);
		if (optionalProduct.isPresent()) {
			productRepository.deleteById(prodId);
			return true;
		}
		return false;
	}
	
	// update stock of a product
	public Boolean updateStock(Long prodId, Long stock) {
		logger.info("Update request for Product with productID {}", prodId);
		Optional<Product> optionalProduct = productRepository.findById(prodId);
		if (stock<10)
			return false;
		else if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			ProductDTO productDTO = ProductDTO.valueOf(product);
			productDTO.setStock(stock);
			Product newProduct = productDTO.createEntity();
			productRepository.save(newProduct);
			return true;
		}
		return false;
	}
	
	// get stock of a product
	public Long getStock(Long prodId) {
		logger.info("Get stock of {}", prodId);
		Long stock=-1L;
		Optional<Product> optionalProduct = productRepository.findByProdId(prodId);
		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			
			ProductDTO productDTO = ProductDTO.valueOf(product);
			stock = productDTO.getStock();
		}
		return stock;
	}
	
	// delete product by seller Id
	public Boolean deleteProductBySellerId(Long sellerId) {
		logger.info("Delete request for Product with sellerId {}", sellerId);
		List<Product> products = productRepository.findBySellerId(sellerId);
		if (!products.isEmpty()) {
			productRepository.deleteBySellerId(sellerId);
			return true;
		}
		return false;
	}

}
