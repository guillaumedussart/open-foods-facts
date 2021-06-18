package org.fantasticfour.bll;


import org.fantasticfour.bo.Category;
import org.fantasticfour.bo.Ingredient;
import org.fantasticfour.dal.DAOFactory;
import org.fantasticfour.dal.ICategoryDAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class CategoryService {

    private static CategoryService single;
    private ICategoryDAO dao = DAOFactory.getCategoryDAO();

    private CategoryService() {
    }//Prevent initialization

    /**
     * get single
     *
     * @return {@link CategoryService}
     * @see CategoryService
     */
    public static CategoryService getSingle() {

        if (null == single) {
            single = new CategoryService();
        }
        return single;
    }

    public Category findByName(EntityManager em, String string) throws SQLException {
        TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c WHERE c.name = :name", Category.class);
        query.setParameter("name", string);
        //return query.getSingleResult();
        List<Category> categories = query.getResultList();
        if (!categories.isEmpty()) {
            return categories.get(0);
        }

        return null;
    }

}
