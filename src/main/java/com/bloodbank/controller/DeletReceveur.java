package com.bloodbank.controller;

import com.bloodbank.dao.ReceveurDAO;
import com.bloodbank.entity.Receveur;
import com.bloodbank.service.ReceveurService;
import com.bloodbank.util.JPAUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeletReceveur extends HttpServlet {

    private Receveur rec;
    private ReceveurService recservice;

    @Override
    public void  init(){
        this.recservice = new ReceveurService(new ReceveurDAO(JPAUtil.getEntityManager()));
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idStr = req.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                Long id = Long.parseLong(idStr);
                recservice.deleteById(id);
                req.setAttribute("receveurs", recservice.Findall());
                req.setAttribute("successMessage", "Receveur supprimé avec succès !");
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("errorMessage", "Erreur lors de la suppression du receveur.");
            }
        } else {
            req.setAttribute("errorMessage", "ID de receveur manquant !");
        }

        req.getRequestDispatcher("/Receveurs.jsp").forward(req, resp);
    }
}