package org.fantasticfour.dal.jpa;

import org.fantasticfour.bo.Category;
import org.fantasticfour.dal.ICategoryDAO;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
		Query query = em.createQuery("SELECT c FROM Category c");
		
		
		
		return null;
	}
	
	public Category findByName(String string) throws SQLException {
		
		TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c WHERE c.name =:name ", Category.class);
		query.setParameter("name", string);
		
		Category category = query.getSingleResult();
		
		return category;
		
	}

	@Override
	public void update(Category o) throws SQLException {
		
		Scanner scanner = new Scanner( System.in );
		
		System.out.println("Entrez le nom d'une catégorie a renommer");
		
		String ancienNom = scanner.nextLine();
		
		Scanner scanner2 = new Scanner( System.in );
		
		System.out.println("Choissisez le nouveau nom de la catégorie");
		
		String nouveauNom = scanner.nextLine();
		
		em.getTransaction().begin();
		
		Query query = em.createQuery("UPDATE Category c SET c.name = :nouveauNom WHERE c.name = :ancienNom");
		
		query.setParameter("nouveauNom", nouveauNom);
		
		query.setParameter("ancienNom", ancienNom);
		
		query.executeUpdate();
		
		em.getTransaction().commit();
		
		
	}

	@Override
	public void delete(Category o) {
		
		Scanner scanner = new Scanner (System.in);
		
		String nom = scanner.nextLine();
		
		em.getTransaction().begin();
		
			Query query = em.createQuery("DELETE FROM o WHERE o.nom = :nom");
			
			query.setParameter("nom", nom);
			
			query.executeUpdate();
		
		em.getTransaction().commit();

	}

	@Override
	public void deleteById(Long aLong) throws SQLException {
		
		em.getTransaction().begin();
		
		Query query = em.createQuery("DELETE FROM Category c WHERE c.id=:id");
		
		query.setParameter("id", aLong);
		
		query.executeUpdate();
		
		em.getTransaction().commit();

	}
}
