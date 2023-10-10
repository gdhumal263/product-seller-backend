package com.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.entities.Product;
import com.product.services.ProductServices;

@RestController
@RequestMapping("/api/product")
public class ProductController
{
	@Autowired
	private ProductServices productServices;

	
	@PostMapping()
	public ResponseEntity<?> saveProduct(@RequestBody Product product)
	{
		return new ResponseEntity<>(productServices.saveProduct(product),HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long productId)
	{
		productServices.deleteProduct(productId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping()
	public ResponseEntity<?> getAllProduct()
	{
		return new ResponseEntity<>(productServices.findAllProducts(),HttpStatus.OK);
	}
}
