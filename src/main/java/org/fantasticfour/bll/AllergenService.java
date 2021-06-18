package org.fantasticfour.bll;


import org.fantasticfour.bo.Allergen;
import org.fantasticfour.bo.Mark;
import org.fantasticfour.dal.DAOFactory;
import org.fantasticfour.dal.IMarkDAO;
import org.fantasticfour.dal.IallergenDAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AllergenService {

    private static AllergenService single;
    private IallergenDAO dao = DAOFactory.getAllergenDAO();
    private AllergenService() {
    }//Prevent initialization

    /**
     * get single
     *
     * @return {@link AllergenService}
     * @see AllergenService
     */
    public static AllergenService getSingle() {

        if (null == single) {
            single = new AllergenService();
        }
        return single;
    }

    public Allergen findByName(EntityManager em, String string) {
        TypedQuery<Allergen> query = em.createQuery("SELECT a FROM Allergen a WHERE a.nom = :name", Allergen.class);
        query.setParameter("name", string);
        List<Allergen> allergen = query.getResultList();
        if (!allergen.isEmpty()) {
            return allergen.get(0);
        }

        return null;
    }

}
