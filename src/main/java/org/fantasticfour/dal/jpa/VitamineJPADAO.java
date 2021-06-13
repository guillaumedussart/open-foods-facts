package org.fantasticfour.dal.jpa;

import java.sql.SQLException;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


import org.fantasticfour.bo.Vitamine;
import org.fantasticfour.dal.IVitamineDAO;

public class VitamineJPADAO implements IVitamineDAO{

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
	private EntityManager em = emf.createEntityManager();
	
	@Override
	public void create(Vitamine o) throws SQLException {
		em.getTransaction().begin();
		
		Vitamine vitamine = new Vitamine();
		
		em.persist(vitamine);
		
		em.getTransaction().commit();
		
	}

	@Override
	public Vitamine findById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Vitamine> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Vitamine findByName(String string) throws SQLException{
		
		TypedQuery<Vitamine> query = em.createQuery("SELECT name FROM vitamines WHERE name = :name",Vitamine.class);
		query.setParameter("name", string);

		Vitamine vitamine = query.getSingleResult();
		return vitamine;
		
	}

	@Override
	public void update(Vitamine o) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Vitamine o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
