package com.bloodbank.controller;

import com.bloodbank.dao.DonneurDAO;
import com.bloodbank.dao.ReceveurDAO;
import com.bloodbank.entity.Donneur;
import com.bloodbank.entity.Receveur;
import com.bloodbank.service.DonneurService;
import com.bloodbank.service.ReceveurService;
import com.bloodbank.util.JPAUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CompatibleDonorsServlet extends HttpServlet {

    private ReceveurService rService;
    private DonneurService service;
    @Override
    public void init() {
        this.service = new DonneurService(new DonneurDAO(JPAUtil.getEntityManager()));
        this.rService = new ReceveurService(new ReceveurDAO(JPAUtil.getEntityManager()));
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String receveurIdParam = req.getParameter("receveurId");
        if (receveurIdParam != null) {
            long receveurId = Long.parseLong(receveurIdParam);
            Receveur receveur = rService.findById(receveurId);
            List<Donneur> compatibles = service.findCompatibleAndAvailableFor(receveur);
            req.setAttribute("donneursCompatibles", compatibles);
            req.getRequestDispatcher("/find.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Receveur ID manquant");
        }
    }

    @Override
    protected void  doPost(HttpServletRequest req , HttpServletResponse resp){


    }



}
