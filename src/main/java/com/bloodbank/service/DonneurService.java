package com.bloodbank.service;

import com.bloodbank.dao.DonneurDAO;
import com.bloodbank.entity.Donneur;
import com.bloodbank.entity.Receveur;
import com.bloodbank.entity.enums.StatutDonneur;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class DonneurService {
    private DonneurDAO dao;

    public DonneurService(DonneurDAO dao) {
        this.dao = dao;
    }

    public void enregistrer(Donneur d) {
        // --- Vérification de l’âge ---
        int age = Period.between(d.getDateNaissance(), LocalDate.now()).getYears();

        boolean contreIndication = d.isHepatiteB() || d.isHepatiteC() || d.isVih() ||
                d.isDiabete() || d.isGrossesse() || d.isAllaitement();

        if (age < 18 || age > 65 || d.getPoids() < 50 || contreIndication) {
            d.setStatut(StatutDonneur.NON_ELIGIBLE);
        } else {
            d.setStatut(StatutDonneur.DISPONIBLE);
        }

        dao.save(d);
    }

    public List<Donneur> Findall(){
        return  dao.findAll();
    }

    public Donneur findById(long id) {
        return  dao.findById(id);
    }


    public List<Donneur> findCompatibleAndAvailableFor(Receveur r) {
        return dao.findAvailableByCompatibleGroup(r.getGroupeSanguin());
    }

    public void DeletById(Long id){
        Donneur d = dao.findById(id);
        if (d != null){
            dao.delete(d);
        }
    }

   public void update(Donneur d){
        dao.update(d);
   }


}
