package com.medical.shopbackend.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.medical.shopbackend.dao.ProductDAO;
import com.medical.shopbackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Product getProduct(int productId) {
		try {
			return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(productId));
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> list() {
		return sessionFactory.getCurrentSession().createQuery("FROM Product ", Product.class).getResultList();
	}

	@Override
	public boolean addProduct(Product product) {

		try {
			sessionFactory.getCurrentSession().persist(product);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateProduct(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteProduct(Product product) {

		try {
			product.setActive(false);
			this.updateProduct(product);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public List<Product> listActiveProducts() {

		String selectActiveProducts = "FROM Product WHERE active = :active";
		return sessionFactory.getCurrentSession().createQuery(selectActiveProducts, Product.class)
				.setParameter("active", true).getResultList();
	}

	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {

		String selectActiveProductsByCategory = "FROM Product WHERE active = :active AND categoryId = :categoryId";
		return sessionFactory.getCurrentSession().createQuery(selectActiveProductsByCategory, Product.class)
				.setParameter("active", true).setParameter("categoryId", categoryId).getResultList();

	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {

		return sessionFactory.getCurrentSession()
				.createQuery("FROM Product WHERE active = :active ORDER BY id", Product.class)
				.setParameter("active", true).setFirstResult(0).setMaxResults(count).getResultList();
	}

	@Override
	public List<Product> getProductsByParam(String param, int count) {

		String query = "FROM Product WHERE active = true ORDER BY " + param + " DESC";

		return sessionFactory.getCurrentSession().createQuery(query, Product.class).setFirstResult(0)
				.setMaxResults(count).getResultList();

	}

}