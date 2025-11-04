package com.bloodbank.dao;

import com.bloodbank.entity.Receveur;
import com.bloodbank.util.JPAUtil;

import javax.persistence.EntityManager;

import  java.util.List;

public class ReceveurDAO {

    private EntityManager em;

    public  ReceveurDAO(){}

    public ReceveurDAO(EntityManager em){
        this.em = em;
    }


    public void  save(Receveur r){
        em.getTransaction().begin();
        em.persist(r);
        em.getTransaction().commit();
    }

    public List<Receveur> findALL() {
        EntityManager emp = JPAUtil.getEntityManager();
        List<Receveur> liste = emp.createQuery(
                        "SELECT r FROM Receveur r LEFT JOIN FETCH r.donneurs", Receveur.class)
                .getResultList();
        return liste;
    }


    public void update(Receveur r) {
        em.getTransaction().begin();
        em.merge(r);
        em.getTransaction().commit();
    }


    public Receveur findById(Long id) {
        return em.find(Receveur.class, id);
    }

    public void  delete(Receveur r){
    em.getTransaction().begin();
    Receveur attach = em.find(Receveur.class , r.getId());

    em.remove(attach);
    em.getTransaction().commit();

    }


}
