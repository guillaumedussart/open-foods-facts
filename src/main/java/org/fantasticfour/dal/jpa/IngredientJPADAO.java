package org.fantasticfour.dal.jpa;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.fantasticfour.bo.Ingredient;
import org.fantasticfour.dal.IIngredientDAO;

public class IngredientJPADAO implements IIngredientDAO {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
	private EntityManager em = emf.createEntityManager();

	@Override
	public void create(Ingredient o) throws SQLException {
		
		em.getTransaction().begin();

		Ingredient ingredient = new Ingredient(o.getName());

		em.persist(ingredient);

		em.getTransaction().commit();

	}

	@Override
	public Ingredient findById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Ingredient> findAll() throws SQLException {
		TypedQuery<Ingredient> query = em.createQuery("SELECT i FROM Ingredient i",Ingredient.class);
		List<Ingredient> listeIngredients = query.getResultList();
		Set<Ingredient> setIngredients = new HashSet<Ingredient>();
		
		for (int i = 0; i < listeIngredients.size(); i++) {
			
			setIngredients.add(listeIngredients.get(i));
			
		}
		return setIngredients;
	}

	public Ingredient findByName(String string) throws SQLException {

		TypedQuery<Ingredient> query = em.createQuery("SELECT i FROM Ingredient i WHERE i.name = :name",Ingredient.class);
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
