package org.fantasticfour.bll;


import org.fantasticfour.bo.Ingredient;
import org.fantasticfour.bo.Product;
import org.fantasticfour.dal.DAOFactory;
import org.fantasticfour.dal.ICategoryDAO;
import org.fantasticfour.dal.IProductDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class ProductService {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("open-food-facts-get");
	private EntityManager em = emf.createEntityManager();

    private static ProductService single;
    private IProductDAO dao = DAOFactory.getProductDAO();
    private ProductService() {
    }//Prevent initialization

    /**
     * get single
     *
     * @return {@link ProductService}
     * @see ProductService
     */
    public static ProductService getSingle() {

        if (null == single) {
            single = new ProductService();
        }
        return single;
    }

    public Product findByName(EntityManager em,String string) {
        System.out.println(string);
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.name = :name", Product.class);
        query.setParameter("name", string);

        List<Product> product = query.getResultList();

        if (!product.isEmpty()) {
            return product.get(0);
        }

        return null;
    }
    

    
    public List<Product> findByNameLike(String string) {
    	
    	
    	TypedQuery<Product> query = em.createQuery("SELECT DISTINCT p FROM Product p left join fetch p.ingredients left join fetch p.additives left join fetch p.allergenes left join fetch p.mark left join fetch p.vitamines left join fetch p.categorie where p.name like :name GROUP BY p.name order by p.name", Product.class);

    	query.setParameter("name", '%'+string+'%');
    	
    	List<Product> products = query.getResultList();
    	if(!products.isEmpty()) {
    		return products;
    	}
		
    	return null;
    }

}
