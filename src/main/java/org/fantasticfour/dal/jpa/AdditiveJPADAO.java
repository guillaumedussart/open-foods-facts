package org.fantasticfour.dal.jpa;

import java.sql.SQLException;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.fantasticfour.bo.Additive;
import org.fantasticfour.dal.IAdditiveDAO;

public class AdditiveJPADAO implements IAdditiveDAO{

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
	private EntityManager em = emf.createEntityManager();
	
	@Override
	public void create(Additive o) throws SQLException {
		// TODO Auto-generated method stub
		
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
	
	public Additive findByName(String string) throws SQLException {
		TypedQuery<Additive> query = em.createQuery("SELECT name FROM categories WHERE name =:name ", Additive.class);
		query.setParameter("name", string);
		
		Additive additive = query.getSingleResult();
		
		return additive;
		
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
