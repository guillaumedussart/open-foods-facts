package org.fantasticfour.dal.jpa;

import org.fantasticfour.bo.*;
import org.fantasticfour.dal.enumerations.EnumDatabaseType;
import org.fantasticfour.dal.jpa.bootstrap.JpaEntityManagerFactory;
import org.fantasticfour.exception.NotFindManagerException;

import javax.persistence.EntityManager;

public class EntityManagerFactoryJPADAO {



    private EntityManagerFactoryJPADAO() {
    }

    private static class EntityManagerFactoryJPADAOHolder {
        private static EntityManagerFactoryJPADAO single;

        static {
            single = new EntityManagerFactoryJPADAO();
        }
    }

    public static EntityManagerFactoryJPADAO getSingle() {

        return EntityManagerFactoryJPADAOHolder.single;
    }

    /**
     * @param type
     * @return
     */
    public EntityManager getManager(EnumDatabaseType type) throws NotFindManagerException {
        switch (type) {
            case CREATE:
                return getJpaEntityManager();
            case SELECT:
                return getJpaEntityManager();
            default:
                throw new NotFindManagerException("Le manager demande est introuvable");

        }
    }

    /**
     *
     */
    private static class EntityManagerHolder  {
        private final static EntityManager ENTITY_MANAGER= new JpaEntityManagerFactory(
                new Class[]{
                        Additive.class,
                        Allergen.class,
                        Product.class,
                        Vitamine.class,
                        Mark.class,
                        Category.class,
                        Ingredient.class
                }
        ).getEntityManager();


    }

    /**
     * @return
     */
    private static EntityManager getJpaEntityManager() {
        return EntityManagerHolder.ENTITY_MANAGER;
    }
}
