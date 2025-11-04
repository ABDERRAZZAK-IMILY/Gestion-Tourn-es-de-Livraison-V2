package com.bloodbank.dao;

import com.bloodbank.entity.BloodCompatibility;
import com.bloodbank.entity.Donneur;
import com.bloodbank.entity.enums.StatutDonneur;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class DonneurDAO {
    private EntityManager em;

    public DonneurDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Donneur d) {
        em.getTransaction().begin();
        em.persist(d);
        em.getTransaction().commit();
    }


    public Donneur findById(Long id){
        return em.find(Donneur.class , id);
    }

    public List<Donneur> findAvailableByCompatibleGroup(String receveurGroup) {
        return em.createQuery("SELECT d FROM Donneur d WHERE d.statut != :statut AND d.receveur IS NULL", Donneur.class)
                .setParameter("statut", StatutDonneur.NON_ELIGIBLE)
                .getResultList()
                .stream()
                .filter(d -> BloodCompatibility.isCompatible(d.getGroupeSanguin(), receveurGroup))
                .collect(Collectors.toList());
    }

    public List<Donneur> findAll() {
        return em.createQuery("SELECT d FROM Donneur d", Donneur.class).getResultList();
    }

    public void delete(Donneur d){
        em.getTransaction().begin();
        Donneur att = em.find(Donneur.class , d.getId());
        em.remove(att);
        em.getTransaction().commit();

    }

    public void update(Donneur d){
        em.getTransaction().begin();
        em.merge(d);
        em.getTransaction().commit();
    }

}
