package org.fantasticfour.dal;

import org.fantasticfour.exception.NotFindProductException;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface IDAO<T, ID> {
	
	void create(T o) throws SQLException;
	T findById(ID id) throws SQLException;
	T findByName(String name) throws SQLException, NotFindProductException;
	List<T> findByNameLike(String name) throws SQLException, NotFindProductException;
	Set<T> findAll() throws SQLException;
	void update(T o) throws SQLException;
	void delete(T o);
	void deleteById(ID id) throws SQLException;
}
