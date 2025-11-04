package com.bloodbank.controller;

import com.bloodbank.entity.Donneur;
import com.bloodbank.entity.Receveur;
import com.bloodbank.service.DonneurService;
import com.bloodbank.service.ReceveurService;

import com.bloodbank.dao.DonneurDAO;
import com.bloodbank.dao.ReceveurDAO;
import com.bloodbank.util.JPAUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AssosiateDonneurReceveur extends HttpServlet {

    private ReceveurService service;
    private DonneurService donorService;


    @Override
    public void init() throws ServletException {
        service = new ReceveurService(new ReceveurDAO(JPAUtil.getEntityManager()));
        donorService = new DonneurService(new DonneurDAO(JPAUtil.getEntityManager()));
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("associate".equals(action)) {
            long receveurId = Long.parseLong(req.getParameter("receveurId"));
            long donneurId = Long.parseLong(req.getParameter("donneurId"));

            Receveur r = service.findById(receveurId);
            Donneur d = donorService.findById(donneurId);

            try {
                service.associateDonneur(r, d);
                req.setAttribute("successMessage", "Association réussie !");
                List<Receveur> receveurs = service.Findall();
                req.setAttribute("receveurs", receveurs);
            } catch (IllegalArgumentException e) {
                req.setAttribute("errorMessage", e.getMessage());
            }

            req.getRequestDispatcher("Receveurs.jsp").forward(req, resp);
        }
    }


}
