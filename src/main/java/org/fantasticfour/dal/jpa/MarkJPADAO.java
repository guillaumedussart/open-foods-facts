package org.fantasticfour.dal.jpa;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;


import org.fantasticfour.bo.Mark;
import org.fantasticfour.dal.IMarkDAO;
import org.fantasticfour.exception.NotFindManagerException;
import org.fantasticfour.exception.NotFindProductException;

public class MarkJPADAO implements IMarkDAO{


	@Override
	public void create(Mark o) throws SQLException {

	}

	@Override
	public Mark findById(Long aLong) throws SQLException {
		return null;
	}

	@Override
	public Set<Mark> findAll() throws SQLException {
		return null;
	}

	@Override
	public void update(Mark o) throws SQLException {

	}

	@Override
	public void delete(Mark o) {

	}

	@Override
	public void deleteById(Long aLong) throws SQLException {

	}

	@Override
	public Mark findByName(String name) throws NotFindProductException, NotFindManagerException {
		return null;
	}

	@Override
	public List<Mark> findByNameLike(String name) throws NotFindProductException, NotFindManagerException {
		return null;
	}
}
