package com.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.entities.Purchase;
import com.product.security.UserPrinciple;
import com.product.services.PurchaseServices;

@RestController
@RequestMapping("api/purchase")
public class PurchaseController 
{
	@Autowired
	private PurchaseServices purchaseServices; 

	
	@PostMapping()
	public ResponseEntity<?> savePurchase(@RequestBody Purchase purchase)
	{
		return new ResponseEntity<>(purchaseServices.savePurchase(purchase),HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<?> getAllPurchasesOfUser(@AuthenticationPrincipal  UserPrinciple userPrinciple)
	{
		return ResponseEntity.ok(purchaseServices.findPurchaseItems(userPrinciple.getId()));
	}
}
