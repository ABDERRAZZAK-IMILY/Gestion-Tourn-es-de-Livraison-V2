package com.bloodbank.entity;

import com.bloodbank.entity.enums.StatutDonneur;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "donneurs")
public class Donneur {

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

    private boolean hepatiteB;
    private boolean hepatiteC;
    private boolean vih;
    private boolean diabete;
    private boolean grossesse;
    private boolean allaitement;

    @Enumerated(EnumType.STRING)
    private StatutDonneur statut;

    @ManyToOne
    @JoinColumn(name = "receveur_id")
    private Receveur receveur;

    public Donneur() {}

    // Getters & Setters
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

    public boolean isHepatiteB() { return hepatiteB; }
    public void setHepatiteB(boolean hepatiteB) { this.hepatiteB = hepatiteB; }

    public boolean isHepatiteC() { return hepatiteC; }
    public void setHepatiteC(boolean hepatiteC) { this.hepatiteC = hepatiteC; }

    public boolean isVih() { return vih; }
    public void setVih(boolean vih) { this.vih = vih; }

    public boolean isDiabete() { return diabete; }
    public void setDiabete(boolean diabete) { this.diabete = diabete; }

    public boolean isGrossesse() { return grossesse; }
    public void setGrossesse(boolean grossesse) { this.grossesse = grossesse; }

    public boolean isAllaitement() { return allaitement; }
    public void setAllaitement(boolean allaitement) { this.allaitement = allaitement; }

    public StatutDonneur getStatut() { return statut; }
    public void setStatut(StatutDonneur statut) { this.statut = statut; }

    public Receveur getReceveur() { return receveur; }
    public void setReceveur(Receveur receveur) { this.receveur = receveur; }


    @Transient
    public int getAge() {
        if (dateNaissance == null) return 0;
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }


}
