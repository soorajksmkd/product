package com.infosys.project.product.validator;

import java.math.BigDecimal;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.infosys.project.product.dto.ProductDTO;


public class ProductValidator {

	Logger logger = LoggerFactory.getLogger(this.getClass());



	public static String validateProduct(ProductDTO productDTO) {
		if (!isValidProductName(productDTO.getProductName()))
			return ("Invalid Product Name Format!!!");
		if (!isValidDescription(productDTO.getDescription()))
			return ("Product Description too long!!!");
		if (!isValidPrice(productDTO.getPrice()))
			return ("Product should be priced atleast 200!");
		if (!isValidStock(productDTO.getStock()))
			return ("Stock should be minimum 10!!");
		return ("OK");

	}



	private static boolean isValidStock(Long stock) {
		if (stock >= 10L)
			return true;
		return false;
	}
	//changes
	private static boolean isValidPrice(BigDecimal price) {
		BigDecimal bd = new BigDecimal(200);
		if (price.compareTo(bd) > 0)
			return true;
		return false;
	}

	private static boolean isValidDescription(String description) {
		if (description.length() <= 500)
			return true;
		return false;
	}

	private static boolean isValidProductName(String productName) {
		String regex = "([a-zA-Z][a-zA-Z ,']{0,98}[a-zA-Z]|[a-zA-Z])";
		if (productName.matches(regex))
			return true;
		return false;
	}
}
