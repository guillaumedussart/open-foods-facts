package org.fantasticfour.dal.jpa;

import java.sql.SQLException;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.fantasticfour.bo.Ingredient;
import org.fantasticfour.dal.IIngredientDAO;

public class IngredientJPADAO implements IIngredientDAO{
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
	private EntityManager em = emf.createEntityManager();

	@Override
	public void create(Ingredient o) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ingredient findById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Ingredient> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Ingredient findByName(String string) throws SQLException{
		
		TypedQuery<Ingredient> query = em.createQuery("SELECT name FROM ingredients WHERE name = :name", Ingredient.class);
		query.setParameter("name", string);
		
		Ingredient ingredient = query.getSingleResult();
		return ingredient;
		
	}

	@Override
	public void update(Ingredient o) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Ingredient o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
