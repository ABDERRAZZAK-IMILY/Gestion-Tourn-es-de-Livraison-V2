package com.bloodbank;

import com.bloodbank.dao.ReceveurDAO;
import com.bloodbank.entity.Donneur;
import com.bloodbank.entity.Receveur;
import com.bloodbank.entity.enums.EtatReceveur;
import com.bloodbank.entity.enums.StatutDonneur;
import com.bloodbank.entity.enums.UrgenceMedicale;
import com.bloodbank.service.ReceveurService;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class ReceveurServiceTest {

    private ReceveurDAO dao;
    private ReceveurService service;
    private List<Receveur> fakeDatabase;

    @Before
    public void setUp() {
        fakeDatabase = new ArrayList<>();


        dao = new ReceveurDAO() {
            @Override
            public void update(Receveur receveur) {
                fakeDatabase.removeIf(r -> r.equals(receveur));
                fakeDatabase.add(receveur);
            }

            @Override
            public void save(Receveur receveur) {
                fakeDatabase.add(receveur);
            }


            public Receveur findById(Long id) {
                return fakeDatabase.stream()
                        .filter(r -> r.getId() == id)
                        .findFirst()
                        .orElse(null);
            }



            public List<Receveur> findAll() {
                return fakeDatabase;
            }
        };

        service = new ReceveurService(dao);
    }

    @Test
    public void testAssociateDonneur_Success() {
        Receveur receveur = createTestReceveur("A+");
        Donneur donneur = createTestDonneur("A+", StatutDonneur.DISPONIBLE);

        service.associateDonneur(receveur, donneur);

        assertEquals(receveur, donneur.getReceveur());
        assertTrue(receveur.getDonneurs().contains(donneur));
        assertEquals(StatutDonneur.NON_DISPONIBLE, donneur.getStatut());

        assertTrue(fakeDatabase.contains(receveur));
    }

    @Test
    public void testAssociateDonneur_IncompatibleBloodGroup() {
        Receveur receveur = createTestReceveur("O-");
        Donneur donneur = createTestDonneur("A+", StatutDonneur.DISPONIBLE);

        try {
            service.associateDonneur(receveur, donneur);
            fail("Exception attendue non levée");
        } catch (IllegalArgumentException e) {
            assertEquals("Incompatibilité sanguine détectée", e.getMessage());
        }

        assertTrue(fakeDatabase.isEmpty());
    }

    @Test
    public void testAssociateDonneur_DonneurNotAvailable() {
        Receveur receveur = createTestReceveur("A+");
        Donneur donneur = createTestDonneur("A+", StatutDonneur.NON_DISPONIBLE);

        try {
            service.associateDonneur(receveur, donneur);
            fail("Exception attendue non levée");
        } catch (IllegalArgumentException e) {
            assertEquals("Le donneur n’est pas disponible ou est déjà associé", e.getMessage());
        }

        assertTrue(fakeDatabase.isEmpty());
    }

    @Test
    public void testAssociateDonneur_DonneurAlreadyAssociated() {
        Receveur receveur1 = createTestReceveur("A+");
        Receveur receveur2 = createTestReceveur("A+");
        Donneur donneur = createTestDonneur("A+", StatutDonneur.DISPONIBLE);
        donneur.setReceveur(receveur1);

        try {
            service.associateDonneur(receveur2, donneur);
            fail("Exception attendue non levée");
        } catch (IllegalArgumentException e) {
            assertEquals("Le donneur n’est pas disponible ou est déjà associé", e.getMessage());
        }

        assertTrue(fakeDatabase.isEmpty());
    }

    // ====== Helpers ======
    private Receveur createTestReceveur(String groupeSanguin) {
        Receveur r = new Receveur();
        r.setGroupeSanguin(groupeSanguin);
        r.setEtat(EtatReceveur.EN_ATTENTE);
        r.setUrgence(UrgenceMedicale.NORMAL);
        r.setDonneurs(new ArrayList<>());
        return r;
    }

    private Donneur createTestDonneur(String groupeSanguin, StatutDonneur statut) {
        Donneur d = new Donneur();
        d.setGroupeSanguin(groupeSanguin);
        d.setStatut(statut);
        d.setDateNaissance(LocalDate.now().minusYears(30));
        d.setPoids(60.0);
        return d;
    }
}
