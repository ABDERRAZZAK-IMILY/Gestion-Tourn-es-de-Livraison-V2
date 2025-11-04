package com.bloodbank.entity;

import com.bloodbank.entity.enums.EtatReceveur;
import com.bloodbank.entity.enums.UrgenceMedicale;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "receveurs")
public class Receveur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String cin;
    private LocalDate dateNaissance;
    private String sexe;
    private String telephone;
    private String groupeSanguin;
    private double poids;

    @Enumerated(EnumType.STRING)
    private EtatReceveur etat;

    @Enumerated(EnumType.STRING)
    private UrgenceMedicale urgence;

    @OneToMany(mappedBy = "receveur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Donneur> donneurs = new ArrayList<>();

    public Receveur() {}


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }

    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }

    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getGroupeSanguin() { return groupeSanguin; }
    public void setGroupeSanguin(String groupeSanguin) { this.groupeSanguin = groupeSanguin; }

    public double getPoids() { return poids; }
    public void setPoids(double poids) { this.poids = poids; }

    public EtatReceveur getEtat() { return etat; }
    public void setEtat(EtatReceveur etat) { this.etat = etat; }

    public UrgenceMedicale getUrgence() { return urgence; }
    public void setUrgence(UrgenceMedicale urgence) { this.urgence = urgence; }

    public List<Donneur> getDonneurs() { return donneurs; }
    public void setDonneurs(List<Donneur> donneurs) { this.donneurs = donneurs; }


    @Transient
    public int getPochesNecessaires() {
        if (this.getUrgence() == null) {
            return 1;
        }

        switch (this.getUrgence()) {
            case NORMAL:
                return 1;
            case URGENT:
                return 3;
            case CRITIQUE:
                return 4;
            default:
                return 1;
        }
    }


    @Transient
    public boolean isPochesCompletes() {
        return this.donneurs != null && this.donneurs.size() >= getPochesNecessaires();
    }

    public void updateEtatAuto() {
        if (isPochesCompletes()) {
            this.etat = EtatReceveur.SATISFAIT;
        } else {
            this.etat = EtatReceveur.EN_ATTENTE;
        }
    }



}
