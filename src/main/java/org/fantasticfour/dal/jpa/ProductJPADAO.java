package org.fantasticfour.dal.jpa;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.fantasticfour.bo.Product;
import org.fantasticfour.dal.IProductDAO;
import org.fantasticfour.dal.enumerations.EnumDatabaseType;
import org.fantasticfour.exception.NotFindManagerException;
import org.fantasticfour.exception.NotFindProductException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ProductJPADAO implements IProductDAO {





    @Override
    public void create(Product o) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public Product findById(Long id) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * find by name
     *
     * @param name name
     * @return {@link Product}
     * @see Product
     */

    public Product findByName(String name) throws NotFindProductException, NotFindManagerException {
        EntityManager em = EntityManagerFactoryJPADAO.getSingle().getManager(EnumDatabaseType.SELECT);
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.name = :name", Product.class);
        query.setParameter("name", name);

        List<Product> product = query.getResultList();

        if (!product.isEmpty()) {
            return product.get(0);
        }

        throw new NotFindProductException("Le produit n'a pas ete trouve");
    }

    public List<Product> findByNameLike(String string) throws NotFindProductException, NotFindManagerException {

        EntityManager em = EntityManagerFactoryJPADAO.getSingle().getManager(EnumDatabaseType.SELECT);

        TypedQuery<Product> query = em.createQuery("SELECT distinct p FROM Product p inner join fetch  p.ingredients i left  join fetch p.additives left  join fetch p.allergenes left join fetch p.mark left join fetch p.vitamines left join fetch p.categorie where p.name like :name  order by p.name", Product.class);

        query.setParameter("name", '%' + string + '%');

        List<Product> products = query.getResultList();
        if (!products.isEmpty()) {
            return products;
        }

        throw new NotFindProductException("Aucun produit trouve");
    }

    @Override
    public Set<Product> findAll() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(Product o) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Product o) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteById(Long id) throws SQLException {
        // TODO Auto-generated method stub

    }


}
