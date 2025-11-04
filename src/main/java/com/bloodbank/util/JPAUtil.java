package com.bloodbank.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.bloodbank.util.EnvLoader;

public class JPAUtil {
    private static final String PERSISTENCE_UNIT_NAME = "bloodBankPU";
    private static EntityManagerFactory entityManagerFactory;

    static {
        EnvLoader.load();
    }
    // Singleton Pattern
    private JPAUtil() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null || !entityManagerFactory.isOpen()) {

            synchronized (JPAUtil.class) {

                if (entityManagerFactory == null || !entityManagerFactory.isOpen()) {
                    entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
                }
            }
        }
        return entityManagerFactory;
    }

    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    public static void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}