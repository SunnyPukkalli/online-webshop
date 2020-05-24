package com.medical.shopbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.medical.shopbackend.dao.CategoryDAO;
import com.medical.shopbackend.dto.Category;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	// get all active categories
	public List<Category> list() {
		// HQL hibernate Query Language -- not table name , entity name
		String selectActiveCategory = " FROM Category WHERE active = :active";

		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		query.setParameter("active", true);

		return query.getResultList();
	}

	// Getting Single category based on ID
	public Category get(int id) {

		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	// Add Category
	@Override
	public boolean addCategory(Category category) {
		try {
			// Add Category to the database table
			sessionFactory.getCurrentSession().persist(category);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	// Update Category
	@Override
	public boolean updateCategory(Category category) {
		try {
			// Update Category to the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	// Delete Category
	@Override
	public boolean deleteCategory(Category category) {
		category.setActive(false);
		try {
			// delete Category to the database table --> means set is-active
			// flag is equal to false
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

}