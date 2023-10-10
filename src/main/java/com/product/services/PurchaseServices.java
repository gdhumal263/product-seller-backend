package com.product.services;

import java.util.List;

import com.product.entities.Purchase;
import com.product.repositories.projection.Purchaseitems;

public interface PurchaseServices
{
	Purchase  savePurchase(Purchase purchase);
	
	List<Purchaseitems> findPurchaseItems(long userId);

}
