package com.medical.shopbackend.dao;

import java.util.List;

import com.medical.shopbackend.dto.Product;

public interface ProductDAO {

	public Product getProduct(int productId);
	public List<Product> list();
	
	public boolean addProduct(Product product);
	public boolean updateProduct(Product product);
	public boolean deleteProduct(Product product);	
	
	public List<Product> getProductsByParam(String param, int count);	

	// business methods
	public List<Product> listActiveProducts();
	public List<Product> listActiveProductsByCategory(int categoryId);
	public List<Product> getLatestActiveProducts(int count);
}
