package org.fantasticfour.bll;


import org.fantasticfour.dal.DAOFactory;
import org.fantasticfour.dal.ICategoryDAO;

import java.sql.SQLException;
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

}
