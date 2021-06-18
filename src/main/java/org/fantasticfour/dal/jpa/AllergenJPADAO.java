package org.fantasticfour.dal.jpa;

import org.fantasticfour.bo.Additive;
import org.fantasticfour.dal.IAdditiveDAO;
import org.fantasticfour.dal.IallergenDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.Set;

public class AllergenJPADAO implements IallergenDAO {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts");
	private EntityManager em = emf.createEntityManager();

	@Override
	public void create(Additive o) throws SQLException {
		em.getTransaction().begin();

		Additive additive = new Additive(o.getName());

		em.persist(additive);

		em.getTransaction().commit();

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
