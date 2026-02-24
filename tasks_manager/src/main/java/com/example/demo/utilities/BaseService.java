package com.example.demo.utilities;

import com.example.demo.pagination.ContentPagination;

public interface BaseService<T, U, ID> {
	U create(U entity);
	U update(U entity, ID id);
	T findById(ID id);
	ContentPagination<T> findAll(int pageNo, int pageSize);
	void delete(ID id);
}
