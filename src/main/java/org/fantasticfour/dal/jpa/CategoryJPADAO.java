package org.fantasticfour.dal.jpa;

import org.fantasticfour.bo.Category;
import org.fantasticfour.dal.ICategoryDAO;

import java.sql.SQLException;
import java.util.Set;

public class CategoryJPADAO implements ICategoryDAO {


	@Override
	public void create(Category o) throws SQLException {

	}

	@Override
	public Category findById(Long aLong) throws SQLException {
		return null;
	}

	@Override
	public Set<Category> findAll() throws SQLException {
		return null;
	}

	@Override
	public void update(Category o) throws SQLException {

	}

	@Override
	public void delete(Category o) {

	}

	@Override
	public void deleteById(Long aLong) throws SQLException {

	}
}
