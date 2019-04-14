package com.jmsilla.crmapitest.testrepositories;

import java.util.*;
import java.util.stream.Collectors;

import com.jmsilla.crmapitest.domain.entities.*;
import com.jmsilla.crmapitest.domain.repositories.*;

public class TestCustomerRepository implements CustomerRepository {
	private Map<Integer, Customer> customersById = new TreeMap<>();
	private Map<String, Customer> customersByName = new HashMap<>();
	private List<String> calls = new ArrayList<>();
	
	public Integer getNextId() {
		calls.add("getNextId()");
		return customersById.keySet().stream().mapToInt(i -> i)
				.max().orElse(1) + 1;
	}

	public List<Customer> findAll() {
		calls.add("findAll()");
		return customersById.values().stream().collect(Collectors.toList());
	}

	public Customer findById(Integer customerId) {
		calls.add("findById(" + customerId + ")");
		return customersById.get(customerId);
	}

	public void create(Customer customer) {
		calls.add("create(" + customer.getId() + ")");
		customersById.put(customer.getId(), customer);
		customersByName.put(customer.getName(), customer);
	}

	public void update(Customer customer) {
		calls.add("update(" + customer.getId() + ")");
		customersById.put(customer.getId(), customer);
		customersByName.put(customer.getName(), customer);
	}

	public void delete(Customer customer) {
		calls.add("delete(" + customer.getId() + ")");
		customersById.remove(customer.getId());
		customersByName.remove(customer.getName());
	}

	public Customer findByName(String name) {
		calls.add("findByName(" + name + ")");
		return customersByName.get(name);
	}
	
	public List<String> getCalls() {
		return calls;
	}
	
	public String getLastCall() {
		if (calls.size() == 0)
			return null;
		
		return calls.get(calls.size() - 1);
	}
}
