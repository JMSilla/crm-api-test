package com.jmsilla.crmapitest.persistence.repositories;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import com.jmsilla.crmapitest.domain.entities.Customer;
import com.jmsilla.crmapitest.domain.repositories.CustomerRepository;
import com.jmsilla.crmapitest.persistence.entities.CustomerEntity;

public class PostgreSQLCustomerRepository implements CustomerRepository {
	private EntityManager em;
	
	private PostgreSQLUserRepository userRepository;
	
	public PostgreSQLCustomerRepository(EntityManager em) {
		this.em = em;
		this.userRepository = new PostgreSQLUserRepository(em);
	}

	@Override
	public Integer getNextId() {
		Query query = em.createQuery("select max(c.id) from CustomerEntity c");
		
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
	public List<Customer> findAll() {
		Query query = em.createQuery("select c from CustomerEntity c");
		List<CustomerEntity> customerEntities = (List<CustomerEntity>)query.getResultList();
		
		return customerEntities.stream().map(Mappers::mapToCustomer)
				.collect(Collectors.toList());
	}

	@Override
	public Customer findById(Integer customerId) {
		return Mappers.mapToCustomer(em.find(CustomerEntity.class, customerId));
	}

	@Override
	public void create(Customer customer) {
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		
		CustomerEntity customerEntity = mapToCustomerEntity(customer);
		
		em.persist(customerEntity);
		em.flush();
		
		transaction.commit();
	}

	private CustomerEntity mapToCustomerEntity(Customer customer) {
		if (customer == null)
			return null;
		
		CustomerEntity customerEntity = new CustomerEntity();

		customerEntity.setId(customer.getId());
		customerEntity.setName(customer.getName());
		customerEntity.setSurname(customer.getSurname());
		
		if (customer.getPhoto() != null) {
			customerEntity.setPhoto(customer.getPhoto().getImageContent());
			customerEntity.setPhotoMimeType(customer.getPhoto().getMimeType());
		}
		
		customerEntity.setCreatedByUser(Mappers.mapToUserEntity(
				userRepository.findByName(customer.getCreatedByUsername())));
		
		String modifiedByUsername = customer.getModifiedByUsername();
		
		if (modifiedByUsername != null) {
			customerEntity.setModifiedByUser(Mappers.mapToUserEntity(
					userRepository.findByName(modifiedByUsername)));
		}
		
		return customerEntity;
	}

	@Override
	public void update(Customer customer) {
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		
		CustomerEntity customerEntity = mapToCustomerEntity(customer);
		
		em.merge(customerEntity);
		em.flush();
		
		transaction.commit();
	}

	@Override
	public void delete(Customer customer) {
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		
		CustomerEntity customerEntity = em.find(CustomerEntity.class, 
				customer.getId());
		
		em.remove(customerEntity);
		em.flush();
		
		transaction.commit();
	}
}
