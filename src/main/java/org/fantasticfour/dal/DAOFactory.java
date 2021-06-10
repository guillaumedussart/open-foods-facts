package org.fantasticfour.dal;


import org.fantasticfour.dal.jpa.CategoryJPADAO;

public final class DAOFactory {


    private DAOFactory() {
    }

    public static ICategoryDAO getCategoryDAO() {

        ICategoryDAO dao = null;

        dao = new CategoryJPADAO();


        return dao;
    }
}
