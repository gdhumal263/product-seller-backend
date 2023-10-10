package com.product.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.product.entities.Product;
import com.product.repositories.ProductRepository;

@Service
public class ProductServicesImpl implements ProductServices
{
	
	private ProductRepository productRepo;

	public ProductServicesImpl(ProductRepository productRepo)
	{
		
		this.productRepo = productRepo;
	}

	
	@Override
	public Product saveProduct(Product product)
	{
		product.setCreateTime(LocalDateTime.now());
		return productRepo.save(product);
	}

	
	@Override
	public void deleteProduct(long id) 
	{
		productRepo.deleteById(id);
		
	}


	@Override
	public List<Product> findAllProducts()
	{
		
		return productRepo.findAll();
	}
	

}
