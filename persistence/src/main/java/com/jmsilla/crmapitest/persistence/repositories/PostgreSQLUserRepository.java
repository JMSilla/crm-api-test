package com.jmsilla.crmapitest.persistence.repositories;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import com.jmsilla.crmapitest.domain.entities.User;
import com.jmsilla.crmapitest.domain.repositories.UserRepository;
import com.jmsilla.crmapitest.persistence.entities.UserEntity;

public class PostgreSQLUserRepository implements UserRepository {
	private EntityManager em;
	
	public PostgreSQLUserRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Integer getNextId() {
		Query query = em.createQuery("select max(u.id) from UserEntity u");
		
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
		Query query = em.createQuery("select u from UserEntity u");
		List<UserEntity> userEntities = (List<UserEntity>)query.getResultList();
		
		return userEntities.stream().map(Mappers::mapToUser)
				.collect(Collectors.toList());
	}

	@Override
	public User findById(Integer userId) {
		return Mappers.mapToUser(em.find(UserEntity.class, userId));
	}

	@Override
	public void create(User user) {
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		
		UserEntity userEntity = Mappers.mapToUserEntity(user);
		
		em.persist(userEntity);
		em.flush();
		
		transaction.commit();
	}

	@Override
	public void update(User user) {
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		
		UserEntity userEntity = Mappers.mapToUserEntity(user);
		
		em.merge(userEntity);
		em.flush();
		
		transaction.commit();
	}

	@Override
	public void delete(User user) {
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		
		UserEntity userEntity = em.find(UserEntity.class, user.getId());
		
		em.remove(userEntity);
		em.flush();
		
		transaction.commit();
	}

	@Override
	public User findByName(String name) {
		Query query = em.createQuery("select u from UserEntity u"
				+ " where name = :name");
		
		query.setParameter("name", name);
		
		UserEntity userEntity;
		
		try {
			userEntity = (UserEntity) query.getSingleResult();
		}
		catch(NoResultException nre) {
			userEntity = null;
		}
		
		return Mappers.mapToUser(userEntity);
	}
}
