package com.product.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.entities.Purchase;
import com.product.repositories.projection.Purchaseitems;



@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> 
{
	@Query("select  prd.name as name,pur.price as price,pur.purchaseTime as purchaseTime from  "
			+ " Purchase pur left join Product prd on prd.id = pur.productId where pur.userId = :userId")
	   List<Purchaseitems> findAllPurchaseOfUser(@Param("userId") Long userId);

}
