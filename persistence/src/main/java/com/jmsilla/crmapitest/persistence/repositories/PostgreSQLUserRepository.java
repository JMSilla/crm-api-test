package com.jmsilla.crmapitest.persistence.repositories;

import java.util.List;

import javax.persistence.*;

import com.jmsilla.crmapitest.domain.entities.User;
import com.jmsilla.crmapitest.domain.repositories.UserRepository;

public class PostgreSQLUserRepository implements UserRepository {
	private EntityManager em;
	
	public PostgreSQLUserRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Integer getNextId() {
		Query query = em.createQuery("select max(u.id) from User u");
		try {
			Integer result = (Integer) query.getSingleResult();
			
			if (result == null)
				return 1;
			
			return result + 1;
		}
		catch (NoResultException nrex) {
			return 1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		Query query = em.createQuery("select u from User u");
		return (List<User>)query.getResultList();
	}

	@Override
	public User findById(Integer userId) {
		return em.find(User.class, userId);
	}

	@Override
	public void create(User user) {
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		
		em.persist(user);
		em.flush();
		
		transaction.commit();
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
