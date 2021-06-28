package org.fantasticfour.dal.jpa;

import org.fantasticfour.bo.Additive;
import org.fantasticfour.dal.IAdditiveDAO;
import org.fantasticfour.dal.IallergenDAO;
import org.fantasticfour.exception.NotFindManagerException;
import org.fantasticfour.exception.NotFindProductException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class AllergenJPADAO implements IallergenDAO {


	@Override
	public void create(Additive o) throws SQLException {

	}

	@Override
	public Additive findById(Long aLong) throws SQLException {
		return null;
	}

	@Override
	public Set<Additive> findAll() throws SQLException {
		return null;
	}

	@Override
	public void update(Additive o) throws SQLException {

	}

	@Override
	public void delete(Additive o) {

	}

	@Override
	public void deleteById(Long aLong) throws SQLException {

	}

	@Override
	public Additive findByName(String name) throws NotFindProductException, NotFindManagerException {
		return null;
	}

	@Override
	public List<Additive> findByNameLike(String name) throws NotFindProductException, NotFindManagerException {
		return null;
	}
}
