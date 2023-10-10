package com.product.services;

import java.util.List;

import com.product.entities.Product;

public interface ProductServices 
{
	
	Product saveProduct(Product product);
	
	void deleteProduct(long id);
	
	List<Product> findAllProducts();

}
