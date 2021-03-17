package com.infosys.project.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.project.product.dto.BuyerDTO;
import com.infosys.project.product.dto.SubscribedProductDTO;
import com.infosys.project.product.entity.Product;
import com.infosys.project.product.entity.SubscribedProduct;
import com.infosys.project.product.repository.ProductRepository;
import com.infosys.project.product.repository.SubscribedProductRepository;

@Service
@Transactional
public class SubscribedProductService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SubscribedProductRepository subscribedProductRepository;
	
	@Autowired
	ProductRepository productRepository;

	public List<SubscribedProductDTO> getAllSubscribedProducts() {
		List<SubscribedProduct> subscribedProducts = subscribedProductRepository.findAll();
		List<SubscribedProductDTO> subscribedProductDTOs = new ArrayList<>();

		for (SubscribedProduct subscribedProduct : subscribedProducts) {
			SubscribedProductDTO subscribedProductDTO = SubscribedProductDTO.valueOf(subscribedProduct);
			subscribedProductDTOs.add(subscribedProductDTO);
		}

		logger.info("Product Details of all subscribed products : {}", subscribedProductDTOs);
		return subscribedProductDTOs;
	}

	public List<SubscribedProductDTO> getAllSubscribedProductsByBuyerId(Long buyerId) {
		List<SubscribedProduct> subscribedProducts = subscribedProductRepository.findByBuyerId(buyerId);
		List<SubscribedProductDTO> subscribedProductDTOs = new ArrayList<>();

		for (SubscribedProduct subscribedProduct : subscribedProducts) {
			SubscribedProductDTO subscribedProductDTO = SubscribedProductDTO.valueOf(subscribedProduct);
			subscribedProductDTOs.add(subscribedProductDTO);
		}
		logger.info("Product Details of all subscribed products by buyerId {}: {}", buyerId, subscribedProductDTOs);
		return subscribedProductDTOs;
	}

	public String addSubscription(BuyerDTO buyerDTO, SubscribedProductDTO subscribedProductDTO) {
		if (buyerDTO.getIsPrivileged()==null || buyerDTO.getIsPrivileged()==0) {
			logger.info("Not a privileged buyer");
			return "You're not a privileged buyer";
		}
		Optional<SubscribedProduct> optionalProduct = subscribedProductRepository.findByBuyerIdAndProdId(subscribedProductDTO.getBuyerId(),subscribedProductDTO.getProdId());
		Optional<Product> optProduct = productRepository.findById(subscribedProductDTO.getProdId());
		if (optionalProduct.isPresent()) {
			logger.info("Product {} already subscribed by Buyer {}", subscribedProductDTO.getProdId(), subscribedProductDTO.getBuyerId());
			return "Product is already subscribed by buyer";
		}else if (optProduct.isEmpty()) {
			logger.info("Product {} does not exist subscribed by Buyer {}", subscribedProductDTO.getProdId());
			return "Product does not exist";
		}
		else {
			SubscribedProduct subscribedProduct = subscribedProductDTO.createEntity();
			subscribedProductRepository.save(subscribedProduct);
			logger.info("Product {} added to Subscribed Product list", subscribedProductDTO.getProdId(), subscribedProductDTO.getBuyerId());
			return "Subscribed successfully";
		}
	}
	public String deleteSubscription(Long subId) {
		Optional<SubscribedProduct> optionalProduct = subscribedProductRepository.findById(subId);
		if (optionalProduct.isPresent()) {
			subscribedProductRepository.deleteById(subId);
			logger.info("Product with subId {} deleted", subId);
			return ("Deleted subscription");
		}
		logger.info("Product with subId {} does not exist in Subscription List", subId);
		return "No such subscription exist";
	}
}
