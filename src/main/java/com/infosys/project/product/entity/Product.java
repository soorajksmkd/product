package com.infosys.project.product.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prodid", nullable = false, length = 11)
	Long prodId;
	
	@Column(name = "brand", nullable = false, length = 255)
	String brand;
	
	@Column(name = "category", nullable = false, length = 255)
	String category;
	
	@Column(name = "description", nullable = false, length = 255)
	String description;
	
	@Column(name = "image", nullable = false, length = 255)
	String image;
	
	@Column(name = "price", nullable = false, precision = 10, scale = 2)
	BigDecimal price;
	
	@Column(name = "productname", nullable = false, length = 255)
	String productName;
	
	@Column(name = "rating", length = 11)
	Long rating;
	
	@Column(name = "sellerid", nullable = false, length = 11)
	Long sellerId;
	
	@Column(name = "stock", nullable = false, length = 11)
	Long stock;
	
	@Column(name = "subcategory", length = 255)
	String subcategory;

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getRating() {
		return rating;
	}

	public void setRating(Long rating) {
		this.rating = rating;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	
	
}
