package org.fantasticfour.bll;


import org.fantasticfour.bo.Mark;
import org.fantasticfour.bo.Product;
import org.fantasticfour.dal.DAOFactory;
import org.fantasticfour.dal.IMarkDAO;
import org.fantasticfour.dal.IProductDAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class MarkService {

    private static MarkService single;
    private IMarkDAO dao = DAOFactory.getMarkDAO();
    private MarkService() {
    }//Prevent initialization

    /**
     * get single
     *
     * @return {@link MarkService}
     * @see MarkService
     */
    public static MarkService getSingle() {

        if (null == single) {
            single = new MarkService();
        }
        return single;
    }

    public Mark findByName(EntityManager em, String string) {
        System.out.println(string);
        TypedQuery<Mark> query = em.createQuery("SELECT m FROM Mark m WHERE m.name = :name", Mark.class);
        query.setParameter("name", string);

        List<Mark> mark = query.getResultList();
        if (!mark.isEmpty()) {
            return mark.get(0);
        }
        return null;
    }

}
