package org.fantasticfour.bll;


import org.fantasticfour.dal.DAOFactory;
import org.fantasticfour.dal.ICategoryDAO;
import org.fantasticfour.dal.IProductDAO;

public class ProductService {

    private static ProductService single;
    private IProductDAO dao = DAOFactory.getProductDAO();

    private ProductService() {
    }//Prevent initialization

    /**
     * get single
     *
     * @return {@link ProductService}
     * @see ProductService
     */
    public static ProductService getSingle() {

        if (null == single) {
            single = new ProductService();
        }
        return single;
    }

}
