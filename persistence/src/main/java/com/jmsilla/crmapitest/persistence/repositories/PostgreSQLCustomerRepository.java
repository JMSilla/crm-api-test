package com.jmsilla.crmapitest.persistence.repositories;

import java.util.List;

import com.jmsilla.crmapitest.domain.entities.Customer;
import com.jmsilla.crmapitest.domain.repositories.CustomerRepository;

public class PostgreSQLCustomerRepository implements CustomerRepository {
	public PostgreSQLCustomerRepository() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Integer getNextId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findById(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Customer user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Customer user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Customer user) {
		// TODO Auto-generated method stub

	}

}
