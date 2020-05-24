package com.medical.shopbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.medical.shopbackend.dao.UserDAO;
import com.medical.shopbackend.dto.Address;
import com.medical.shopbackend.dto.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addUser(User user) {

		try {
			sessionFactory.getCurrentSession().persist(user);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	
	@Override
	public User getUserByEmail(String email) {

		String selectQuery = "FROM User WHERE email = :email";

		try {
			return sessionFactory.getCurrentSession().createQuery(selectQuery, User.class).setParameter("email", email)
					.getSingleResult();

		} catch (Exception ex) {

			return null;
		}

	}
	
	@Override
	public User getUser(int id) {
		try {
			return sessionFactory.getCurrentSession().get(User.class, id);
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public Address getBillingAddress(int userId) {

		String selectQuery = "FROM Address WHERE userId = :userId AND billing = :billing ";

		try {
			return sessionFactory.getCurrentSession().createQuery(selectQuery, Address.class)
					.setParameter("userId", userId).setParameter("billing", true).getSingleResult();

		} catch (Exception ex) {
			return null;
		}

	}
	
	
	@Override
	public List<Address> getShippingAddress(int userId) {
		String selectQuery = "FROM Address WHERE userId = :userId AND shipping = :shipping ";

		try {
			return sessionFactory.getCurrentSession().createQuery(selectQuery, Address.class)
					.setParameter("userId", userId).setParameter("shipping", true).getResultList();

		} catch (Exception ex) {
			return null;
		}

	}
	
	@Override
	public boolean addAddress(Address address) {

		try {
			sessionFactory.getCurrentSession().persist(address);
			return true;
		} catch (Exception ex) {

			return false;
		}

	}
	
	
	@Override
	public boolean updateAddress(Address address) {
		try {			
			sessionFactory.getCurrentSession().update(address);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
	
	@Override
	public Address getAddress(int addressId) {
		try {
			return sessionFactory.getCurrentSession().get(Address.class, addressId);
		} catch (Exception ex) {
			return null;
		}
	}

}