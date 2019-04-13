package com.jmsilla.crmapitest.domain.repositories;

import java.util.List;

public interface BaseRepository<T> {
	Integer getNextId();
	List<T> findAll();
	T findById(Integer userId);
	void create(T user);
	void update(T user);
	void delete(T user);
}
