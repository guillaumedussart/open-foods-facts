package org.fantasticfour.bll;


import org.fantasticfour.bo.Product;
import org.fantasticfour.dal.DAOFactory;
import org.fantasticfour.dal.IProductDAO;
import org.fantasticfour.exception.NotFindManagerException;
import org.fantasticfour.exception.NotFindProductException;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
	


    private IProductDAO dao = DAOFactory.getProductDAO();


    private ProductService() {
    }//Prevent initialization

    private static class ProductServiceHolder {
        private static ProductService single;

        static {
            single = new ProductService();
        }
    }

    public static ProductService getSingle() {

        return ProductServiceHolder.single;
    }

    public Product findByName(String name) throws SQLException, NotFindProductException, NotFindManagerException {
        return dao.findByName(name);
    }
    

    
    public List<Product> findByNameLike(String name) throws SQLException, NotFindProductException, NotFindManagerException {
    	
    	return dao.findByNameLike(name);
    }

}
