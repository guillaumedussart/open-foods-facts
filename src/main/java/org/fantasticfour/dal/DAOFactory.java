package org.fantasticfour.dal;


import org.fantasticfour.dal.jpa.*;

public final class DAOFactory {


    private DAOFactory() {
    }

    private static class DAOFactoryHolder {
        private static DAOFactory single;

        static {
            single = new DAOFactory();
        }
    }

    public static DAOFactory getSingle() {

        return DAOFactoryHolder.single;
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

    public static IallergenDAO getAllergenDAO() {

        AllergenJPADAO dao = null;

        dao = new AllergenJPADAO();


        return dao;
    }

    public static IAdditiveDAO getAdditiveDAO() {

        AdditiveJPADAO dao = null;

        dao = new AdditiveJPADAO();


        return dao;
    }
}
