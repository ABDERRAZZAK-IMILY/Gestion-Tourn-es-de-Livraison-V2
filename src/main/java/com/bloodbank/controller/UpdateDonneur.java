package com.bloodbank.controller;

import com.bloodbank.dao.DonneurDAO;
import com.bloodbank.entity.Donneur;
import com.bloodbank.entity.enums.StatutDonneur;
import com.bloodbank.service.DonneurService;
import com.bloodbank.util.JPAUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateDonneur extends HttpServlet {

    private DonneurService donserv;
    @Override
    public void init(){
        this.donserv = new DonneurService(new DonneurDAO(JPAUtil.getEntityManager()));
    }

    @Override
    protected void doPost(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));

        Donneur don = donserv.findById(id);
        don.setNom(req.getParameter("nom"));
        don.setPrenom(req.getParameter("prenom"));
        don.setSexe(req.getParameter("sexe"));
        don.setCin(req.getParameter("cin"));
        don.setGroupeSanguin(req.getParameter("groupeSanguin"));
        don.setPoids(Double.parseDouble(req.getParameter("poids")));
        don.setStatut(StatutDonneur.valueOf(req.getParameter("statut")));

        donserv.update(don);

        req.setAttribute("succes" , "donneur updated succecfuly");


        req.setAttribute("donneurs" , donserv.Findall());

        req.getRequestDispatcher("/Donneurs.jsp").forward(req , resp);


    }
}
