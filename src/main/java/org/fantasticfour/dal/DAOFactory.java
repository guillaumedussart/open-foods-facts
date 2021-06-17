package org.fantasticfour.dal;


import org.fantasticfour.dal.jpa.CategoryJPADAO;
import org.fantasticfour.dal.jpa.IngredientJPADAO;
import org.fantasticfour.dal.jpa.MarkJPADAO;
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

    public static IIngredientDAO getIngredientDAO() {

        IngredientJPADAO dao = null;

        dao = new IngredientJPADAO();


        return dao;
    }

    public static IMarkDAO getMarkDAO() {

        MarkJPADAO dao = null;

        dao = new MarkJPADAO();


        return dao;
    }
}
