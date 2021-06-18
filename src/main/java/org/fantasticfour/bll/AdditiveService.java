package org.fantasticfour.bll;


import org.fantasticfour.bo.Additive;
import org.fantasticfour.bo.Allergen;
import org.fantasticfour.dal.DAOFactory;
import org.fantasticfour.dal.IAdditiveDAO;
import org.fantasticfour.dal.IallergenDAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AdditiveService {

    private static AdditiveService single;
    private IAdditiveDAO dao = DAOFactory.getAdditiveDAO();

    private AdditiveService() {
    }//Prevent initialization

    /**
     * get single
     *
     * @return {@link AdditiveService}
     * @see AdditiveService
     */
    public static AdditiveService getSingle() {

        if (null == single) {
            single = new AdditiveService();
        }
        return single;
    }

    public Additive findByName(EntityManager em, String string) {
        TypedQuery<Additive> query = em.createQuery("SELECT a FROM Additive a WHERE a.name = :name", Additive.class);
        query.setParameter("name", string);
        List<Additive> additive = query.getResultList();
        if (!additive.isEmpty()) {
            return additive.get(0);
        }

        return null;
    }

}
