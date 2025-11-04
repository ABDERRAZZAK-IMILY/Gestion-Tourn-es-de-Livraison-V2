package com.bloodbank.service;

import com.bloodbank.entity.BloodCompatibility;
import com.bloodbank.entity.Donneur;
import  com.bloodbank.entity.Receveur;
import com.bloodbank.dao.ReceveurDAO;
import com.bloodbank.entity.enums.StatutDonneur;

import java.util.List;


public class ReceveurService {

    private static final int MAX_DONNEURS = 4;
    private ReceveurDAO dao;

    public  ReceveurService(ReceveurDAO dao){
        this.dao = dao;
    }

    public void  enregistrer (Receveur r){

        dao.save(r);

    }


    public List<Receveur> Findall(){

        return  dao.findALL();
    }


    public void associateDonneur(Receveur r, Donneur d) {
        if (!BloodCompatibility.isCompatible(d.getGroupeSanguin(), r.getGroupeSanguin())) {
            throw new IllegalArgumentException("Incompatibilité sanguine détectée");
        }

        if (d.getStatut() != StatutDonneur.DISPONIBLE || d.getReceveur() != null) {
            throw new IllegalArgumentException("Le donneur n’est pas disponible ou est déjà associé");
        }

        if (r.getDonneurs().size() >= MAX_DONNEURS) {
            throw new IllegalStateException("Le receveur ne peut pas recevoir plus de " + MAX_DONNEURS + " dons !");
        }
        r.getDonneurs().add(d);
        d.setReceveur(r);
        d.setStatut(StatutDonneur.NON_DISPONIBLE);
        r.updateEtatAuto();

        dao.update(r);
    }

    public Receveur findById(long id) {
        return dao.findById(id);
    }


    public void dissociateDonneur(Receveur r, Donneur d) {
        r.getDonneurs().remove(d);
        d.setReceveur(null);
        d.setStatut(StatutDonneur.DISPONIBLE);

        r.updateEtatAuto();

        dao.update(r);
    }


    public void deleteById(Long id) {
        Receveur r = dao.findById(id);
        if (r != null) {
            dao.delete(r);
        }
    }


    public void update(Receveur r){
        dao.update(r);
    }


}
