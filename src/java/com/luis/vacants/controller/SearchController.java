/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luis.vacants.controller;

import com.luis.vacants.dao.DbConnection;
import com.luis.vacants.dao.VacantDao;
import com.luis.vacants.model.Vacant;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luis
 */
@WebServlet(name = "SearchController", urlPatterns = {"/SearchController"})
public class SearchController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Recibo la cadena de busqueda del formulario
        String q = request.getParameter("query");
        
        DbConnection conn = new DbConnection();
        VacantDao vacantDao = new VacantDao(conn);
        List<Vacant> vacants = vacantDao.getByQuery(q);
        conn.disconnect();
        
        //Comparto la variable msg para poder accederla desde la vista con Expresion language
        request.setAttribute("vacants", vacants);
        RequestDispatcher rd;
        
        //Envio la respuesta. Renderizo la vista detail.jsp
        rd = request.getRequestDispatcher("/list_vacants.jsp");
        rd.forward(request, response);
        
    }

}
