package org.fantasticfour.dal.jpa;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.fantasticfour.bo.Ingredient;
import org.fantasticfour.dal.IIngredientDAO;
import org.fantasticfour.exception.NotFindManagerException;
import org.fantasticfour.exception.NotFindProductException;

public class IngredientJPADAO implements IIngredientDAO {


	@Override
	public void create(Ingredient o) throws SQLException {

	}

	@Override
	public Ingredient findById(Long aLong) throws SQLException {
		return null;
	}

	@Override
	public Set<Ingredient> findAll() throws SQLException {
		return null;
	}

	@Override
	public void update(Ingredient o) throws SQLException {

	}

	@Override
	public void delete(Ingredient o) {

	}

	@Override
	public void deleteById(Long aLong) throws SQLException {

	}

	@Override
	public Ingredient findByName(String name) throws NotFindProductException, NotFindManagerException {
		return null;
	}

	@Override
	public List<Ingredient> findByNameLike(String name) throws NotFindProductException, NotFindManagerException {
		return null;
	}
}
