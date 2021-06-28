package org.fantasticfour.dal.jpa;

import org.fantasticfour.bo.Category;
import org.fantasticfour.dal.ICategoryDAO;
import org.fantasticfour.exception.NotFindManagerException;
import org.fantasticfour.exception.NotFindProductException;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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

	@Override
	public Category findByName(String name) throws NotFindProductException, NotFindManagerException {
		return null;
	}

	@Override
	public List<Category> findByNameLike(String name) throws NotFindProductException, NotFindManagerException {
		return null;
	}
}
