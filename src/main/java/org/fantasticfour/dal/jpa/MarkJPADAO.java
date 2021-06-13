package org.fantasticfour.dal.jpa;

import java.sql.SQLException;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.fantasticfour.bo.Mark;
import org.fantasticfour.dal.IMarkDAO;

public class MarkJPADAO implements IMarkDAO{

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
	private EntityManager em = emf.createEntityManager();
	
	@Override
	public void create(Mark o) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Mark findById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Mark> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Mark findByName(String string) throws SQLException{
		TypedQuery<Mark> query = em.createQuery("SELECT m.name FROM Mark m WHERE m.name = :name",Mark.class);
		query.setParameter("name", string);

		Mark mark = query.getSingleResult();
		return mark;
		
	}

	@Override
	public void update(Mark o) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Mark o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
