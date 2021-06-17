package org.fantasticfour.bll;


import org.fantasticfour.bo.Ingredient;
import org.fantasticfour.dal.DAOFactory;
import org.fantasticfour.dal.IIngredientDAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class IngredientService {

    private static IngredientService single;
    private IIngredientDAO dao = DAOFactory.getIngredientDAO();

    private IngredientService() {
    }//Prevent initialization

    /**
     * get single
     *
     * @return {@link IngredientService}
     * @see IngredientService
     */
    public static IngredientService getSingle() {

        if (null == single) {
            single = new IngredientService();
        }
        return single;
    }

    public Ingredient findByName(EntityManager em, String string) throws SQLException {
        TypedQuery<Ingredient> query = em.createQuery("SELECT i FROM Ingredient i WHERE i.name = :name", Ingredient.class);
        query.setParameter("name", string);
        return query.getSingleResult();
        /*List<Ingredient> ingredient = query.getResultList();
        if (!ingredient.isEmpty()) {
            return ingredient.get(0);
        }

        return null;*/
    }
}
