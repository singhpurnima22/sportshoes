package com.phase3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phase3.model.Product;
import com.phase3.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productrepo;
	
	public List<Product> getAllProduct()
	{
		return productrepo.findAll();
	}
}
