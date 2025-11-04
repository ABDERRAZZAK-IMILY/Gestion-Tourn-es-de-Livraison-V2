package com.bloodbank;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bloodbank.util.EnvLoader;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestConnection {
    @Test
    public void testEntityManagerConnection() {
        try {
            EnvLoader.load();
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("bloodBankPU");
            EntityManager em = emf.createEntityManager();
            assertNotNull("EntityManager est null !", em);
            em.close();
            emf.close();
        } catch (Exception e) {
            fail("Erreur JPA : " + e.getMessage());
        }
    }
}