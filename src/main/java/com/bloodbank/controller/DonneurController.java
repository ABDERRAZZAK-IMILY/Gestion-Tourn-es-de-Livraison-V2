package com.bloodbank.controller;

import com.bloodbank.dao.DonneurDAO;
import com.bloodbank.entity.Donneur;
import com.bloodbank.service.DonneurService;
import com.bloodbank.util.JPAUtil;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class DonneurController extends HttpServlet {

    private DonneurService service;

    @Override
    public void init() {
        service = new DonneurService(new DonneurDAO(JPAUtil.getEntityManager()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Donneur d = new Donneur();
        d.setNom(req.getParameter("nom"));
        d.setPrenom(req.getParameter("prenom"));
        d.setCin(req.getParameter("cin"));
        d.setDateNaissance(LocalDate.parse(req.getParameter("dateNaissance")));
        d.setSexe(req.getParameter("sexe"));
        d.setTelephone(req.getParameter("telephone"));
        d.setGroupeSanguin(req.getParameter("groupeSanguin"));
        d.setPoids(Double.parseDouble(req.getParameter("poids")));

        d.setHepatiteB(req.getParameter("hepatiteB") != null);
        d.setHepatiteC(req.getParameter("hepatiteC") != null);
        d.setVih(req.getParameter("vih") != null);
        d.setDiabete(req.getParameter("diabete") != null);
        d.setGrossesse(req.getParameter("grossesse") != null);
        d.setAllaitement(req.getParameter("allaitement") != null);

        service.enregistrer(d);

        req.setAttribute("successMessage", " Donneur enregistré avec succès !");
        req.getRequestDispatcher("creation.jsp").forward(req, resp);
    }


    @Override
    protected  void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {

      List<Donneur> don  =  service.Findall();

      req.setAttribute("donneurs" , don);

      req.getRequestDispatcher("Donneurs.jsp").forward(req , resp);




    }


}
