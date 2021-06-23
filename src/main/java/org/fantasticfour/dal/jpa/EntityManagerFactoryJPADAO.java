package org.fantasticfour.dal.jpa;

import org.fantasticfour.dal.enumerations.EnumDatabaseType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryJPADAO {

    private EntityManager em;
    private EntityManagerFactory emf;

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

    public EntityManager getManager(EnumDatabaseType type) {
        switch (type) {
            case CREATE:
                emf = Persistence.createEntityManagerFactory("open-food-facts");
                em = emf.createEntityManager();
                return em;
            case SELECT:
                emf = Persistence.createEntityManagerFactory("open-food-facts-get");
                em = emf.createEntityManager();
                return em;
        }
        return null;
    }

}
