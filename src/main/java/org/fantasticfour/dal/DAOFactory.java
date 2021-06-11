package org.fantasticfour.dal;


import org.fantasticfour.dal.jpa.CategoryJPADAO;
import org.fantasticfour.dal.jpa.ProductJPADAO;

public final class DAOFactory {


    private DAOFactory() {
    }

    public static ICategoryDAO getCategoryDAO() {

        ICategoryDAO dao = null;

        dao = new CategoryJPADAO();


        return dao;
    }

    public static IProductDAO getProductDAO() {

        IProductDAO dao = null;

        dao = new ProductJPADAO();


        return dao;
    }
}
