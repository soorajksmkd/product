package com.infosys.project.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infosys.project.product.dto.BuyerDTO;
import com.infosys.project.product.dto.SubscribedProductDTO;
import com.infosys.project.product.service.ProductService;
import com.infosys.project.product.service.SubscribedProductService;

@RestController
public class SubscribedProductController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SubscribedProductService subscribedProductService;

	@Autowired
	ProductService productService;

	@Value("${buyer.uri}")
	String buyerUri;

	// 1 Fetches the subscribed-products table
	@GetMapping(value = "/subscriptions", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SubscribedProductDTO> getAllSubscribedProducts() {
		logger.info("Fetching all subscription details");
		return subscribedProductService.getAllSubscribedProducts();
	}

	// 2 Fetches subscription details for particular buyer
	@GetMapping(value = "/subscriptions/{buyerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SubscribedProductDTO> getAllSubscribedProductsByBuyerId(@PathVariable Long buyerId) {
		logger.info("Fetching all subscribed product details");
		return subscribedProductService.getAllSubscribedProductsByBuyerId(buyerId);
	}

	// 3 Add a subscription
	@PostMapping(value = "/subscriptions", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addSubscription(@RequestBody SubscribedProductDTO subscribedProductDTO) {
		logger.info("Add subscription for buyer {}", subscribedProductDTO.getBuyerId());
		RestTemplate restTemplate = new RestTemplate();
		BuyerDTO buyerDTO = restTemplate.getForObject(buyerUri + "/"+subscribedProductDTO.getBuyerId(), BuyerDTO.class);
		if (buyerDTO==null)
			return new ResponseEntity<>("Buyer does not exist", HttpStatus.OK);
		String message = subscribedProductService.addSubscription(buyerDTO, subscribedProductDTO);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	// 4 delete subscription
	@DeleteMapping(value = "/subscriptions/{subId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteSubscription(@PathVariable Long subId) {
		logger.info("Delete subscription for subId {}", subId);
		String message = subscribedProductService.deleteSubscription(subId);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
}
