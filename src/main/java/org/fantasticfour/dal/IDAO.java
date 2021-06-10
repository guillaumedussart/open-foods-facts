package org.fantasticfour.dal;

import java.sql.SQLException;
import java.util.Set;

public interface IDAO<T, ID> {
	
	void create(T o) throws SQLException;
	T findById(ID id) throws SQLException;
	Set<T> findAll() throws SQLException;
	void update(T o) throws SQLException;
	void delete(T o);
	void deleteById(ID id) throws SQLException;
}
