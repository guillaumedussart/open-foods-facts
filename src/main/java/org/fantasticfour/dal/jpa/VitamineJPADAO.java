package org.fantasticfour.dal.jpa;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.fantasticfour.bo.Vitamine;
import org.fantasticfour.dal.IVitamineDAO;
import org.fantasticfour.exception.NotFindManagerException;
import org.fantasticfour.exception.NotFindProductException;

public class VitamineJPADAO implements IVitamineDAO{


	@Override
	public void create(Vitamine o) throws SQLException {

	}

	@Override
	public Vitamine findById(Long aLong) throws SQLException {
		return null;
	}

	@Override
	public Set<Vitamine> findAll() throws SQLException {
		return null;
	}

	@Override
	public void update(Vitamine o) throws SQLException {

	}

	@Override
	public void delete(Vitamine o) {

	}

	@Override
	public void deleteById(Long aLong) throws SQLException {

	}

	@Override
	public Vitamine findByName(String name) throws NotFindProductException, NotFindManagerException {
		return null;
	}

	@Override
	public List<Vitamine> findByNameLike(String name) throws NotFindProductException, NotFindManagerException {
		return null;
	}
}
