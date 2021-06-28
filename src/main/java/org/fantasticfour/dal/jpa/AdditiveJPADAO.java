package org.fantasticfour.dal.jpa;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.fantasticfour.bo.Additive;
import org.fantasticfour.dal.IAdditiveDAO;
import org.fantasticfour.exception.NotFindManagerException;
import org.fantasticfour.exception.NotFindProductException;

public class AdditiveJPADAO implements IAdditiveDAO {


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
