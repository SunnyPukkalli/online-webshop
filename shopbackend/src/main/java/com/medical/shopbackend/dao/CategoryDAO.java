package com.medical.shopbackend.dao;

import java.util.List;

import com.medical.shopbackend.dto.Category;

public interface CategoryDAO {
	
	
	public Category get(int id);
	public boolean addCategory(Category category);
	public boolean updateCategory(Category category);
	public boolean deleteCategory(Category category);

	public List<Category> list();
}
