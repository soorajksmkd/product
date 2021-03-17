package com.infosys.project.product.dto;

import com.infosys.project.product.entity.SubscribedProduct;

public class SubscribedProductDTO {

	Long subId;
	
	Long buyerId;
	
	Long prodId;
	
	Long quantity;

	public SubscribedProductDTO(Long subId, Long buyerId, Long prodId, Long quantity) {
		this.subId = subId;
		this.buyerId = buyerId;
		this.prodId = prodId;
		this.quantity = quantity;
	}

	public SubscribedProductDTO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "SubscribedProductDTO [subId=" + subId + ", buyer=" + buyerId + ", product=" + prodId + ", quantity="
				+ quantity + "]";
	}

	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public Long getProdId() {
		return prodId;
	}

	public void setProductId(Long prodId) {
		this.prodId = prodId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	//converts entity to DTO
	public static SubscribedProductDTO valueOf(SubscribedProduct subscribedProduct) {
		SubscribedProductDTO subscribedProductDTO = new SubscribedProductDTO();
		
		subscribedProductDTO.setSubId(subscribedProduct.getSubId());
		subscribedProductDTO.setBuyerId(subscribedProduct.getBuyerId());
		subscribedProductDTO.setProductId(subscribedProduct.getProdId());		
		subscribedProductDTO.setQuantity(subscribedProduct.getQuantity());
		
		return subscribedProductDTO;
	}
	
	//converts DTO to entity
	public SubscribedProduct createEntity() {
		SubscribedProduct subscribedProduct = new SubscribedProduct();
		
		subscribedProduct.setSubId(this.getSubId());
		subscribedProduct.setBuyerId(this.getBuyerId());
		subscribedProduct.setProdId(this.getProdId());
		subscribedProduct.setQuantity(this.getQuantity());
		
		return subscribedProduct;
	}
	
	

}

