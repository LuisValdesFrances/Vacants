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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lvaldes
 */
@WebServlet(name = "VacantController", urlPatterns = {"/VacantController"})
public class VacantController extends HttpServlet {

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
        //Rebibo parametros
        Vacant vacant = new Vacant(0);
        vacant.setName(request.getParameter("nombre"));
        vacant.setDescription(request.getParameter("descripcion"));
        vacant.setDetail(request.getParameter("detalle"));
        System.out.println("Nueva vacante recibida: " + vacant);
        
        //Lo inserto en la base de datos
        DbConnection conn = new DbConnection();
        VacantDao vacantDao = new VacantDao(conn);
        boolean status = vacantDao.insert(vacant);
        
        String msg = "";
        if(status)
            msg = "Vacante insertada correctamente";
        else
            msg = "La vacante no se ha podido insertar";
        
        conn.disconnect();
        RequestDispatcher rd;
        //Comparto la variable msg para accederla desde la vista
        request.setAttribute("message", msg);
        //Envio la respuesta. Renderizo la vista message.jsp
        rd = request.getRequestDispatcher("/result.jsp");
        rd.forward(request, response);//request -> include("message")
        
        
        
    }

}
