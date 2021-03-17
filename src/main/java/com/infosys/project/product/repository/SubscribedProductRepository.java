package com.infosys.project.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.infosys.project.product.entity.SubscribedProduct;

public interface SubscribedProductRepository extends JpaRepository<SubscribedProduct, Long> {

	List<SubscribedProduct> findByBuyerId(Long buyerId);

	Optional<SubscribedProduct> findByBuyerIdAndProdId(Long buyerId, Long prodId);

}
