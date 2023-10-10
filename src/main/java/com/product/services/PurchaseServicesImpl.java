package com.product.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.product.entities.Purchase;
import com.product.repositories.PurchaseRepository;
import com.product.repositories.projection.Purchaseitems;

@Service
public class PurchaseServicesImpl  implements PurchaseServices
{

	private PurchaseRepository purchaseRepo;

	
	public PurchaseServicesImpl(PurchaseRepository purchaseRepo)
	{
		
		this.purchaseRepo = purchaseRepo;
	}

	
	@Override
	public Purchase savePurchase(Purchase purchase) 
	{
		purchase.setPurchaseTime(LocalDateTime.now());
		return purchaseRepo.save(purchase);
	}


	@Override
	public List<Purchaseitems> findPurchaseItems(long userId)
	{
		
		return purchaseRepo.findAllPurchaseOfUser(userId);
	}
	
}
