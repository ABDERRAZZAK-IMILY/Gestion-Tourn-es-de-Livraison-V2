package com.bloodbank.controller;

import com.bloodbank.dao.ReceveurDAO;
import com.bloodbank.entity.Receveur;
import com.bloodbank.entity.enums.EtatReceveur;
import com.bloodbank.entity.enums.UrgenceMedicale;
import com.bloodbank.service.ReceveurService;
import com.bloodbank.util.JPAUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateReceveur extends HttpServlet {


private ReceveurService service;

    @Override
    public void init(){

        this.service = new ReceveurService(new ReceveurDAO(JPAUtil.getEntityManager()));
    }

   @Override
    public void doPost(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {

        long id = Long.parseLong(req.getParameter("id"));

     Receveur rec =   service.findById(id);

       rec.setNom(req.getParameter("nom"));
       rec.setPrenom(req.getParameter("prenom"));
       rec.setCin(req.getParameter("cin"));
       rec.setTelephone(req.getParameter("telephone"));
       rec.setGroupeSanguin(req.getParameter("groupeSanguin"));
       rec.setUrgence(UrgenceMedicale.valueOf(req.getParameter("urgence")));
     service.update(rec);
       req.getSession().setAttribute("successMessage", "Receveur mis à jour avec succès !");
       resp.sendRedirect(req.getContextPath() + "/receveurs");
   }





}
