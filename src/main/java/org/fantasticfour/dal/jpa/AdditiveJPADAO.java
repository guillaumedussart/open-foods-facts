package org.fantasticfour.dal.jpa;

import java.sql.SQLException;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.fantasticfour.bo.Additive;
import org.fantasticfour.bo.Category;
import org.fantasticfour.dal.IAdditiveDAO;

public class AdditiveJPADAO implements IAdditiveDAO {



	@Override
	public void create(Additive o) throws SQLException {


	}

	@Override
	public Additive findById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Additive> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void update(Additive o) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Additive o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Long id) throws SQLException {
		// TODO Auto-generated method stub

	}

}
