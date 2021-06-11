package org.fantasticfour.dal.jpa;

import org.fantasticfour.bo.Category;
import org.fantasticfour.dal.ICategoryDAO;

import java.sql.SQLException;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CategoryJPADAO implements ICategoryDAO {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
	private EntityManager em = emf.createEntityManager();
	
	@Override
	public void create(Category o) throws SQLException {
		
		em.getTransaction().begin();
		
		Category category = new Category(o.getName());
		
		em.persist(category);
		
		em.getTransaction().commit();

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
}
