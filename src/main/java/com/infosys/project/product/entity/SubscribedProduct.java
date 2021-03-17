package com.infosys.project.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subscribedproduct")
public class SubscribedProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subid", nullable = false, length = 11)
	Long subId;
	
	@Column(name = "buyerid", nullable = false, length = 11)
	Long buyerId;
	
	@Column(name = "prodid", nullable = false, length = 11)
	Long prodId;
	
	@Column(name = "quantity", nullable = false, length = 11)
	Long quantity;

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

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	
}
