package com.bloodbank.controller;

import com.bloodbank.dao.DonneurDAO;
import com.bloodbank.service.DonneurService;
import com.bloodbank.util.JPAUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeletDonneur extends HttpServlet {

 private DonneurService donser;


   @Override
    public void init(){
     this.donser = new DonneurService(new DonneurDAO(JPAUtil.getEntityManager()));
 }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strId = req.getParameter("id");

        if (strId != null && !strId.isEmpty()) {
            try {
                Long id = Long.parseLong(strId);
                donser.DeletById(id);

                req.getSession().setAttribute("successMessage", "Donneur supprimé avec succès !");

                resp.sendRedirect(req.getContextPath() + "/donneurs");

            } catch (Exception e) {
                e.printStackTrace();
                req.getSession().setAttribute("errorMessage", "Erreur lors de la suppression du donneur");
                resp.sendRedirect(req.getContextPath() + "/donneurs");
                return;
            }
        } else {
            req.getSession().setAttribute("errorMessage", "ID de donneur manquant !");
            resp.sendRedirect(req.getContextPath() + "/donneurs");
        }
    }

 }