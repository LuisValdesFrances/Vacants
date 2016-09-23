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
 * @author lvaldes
 */
@WebServlet(name = "SiteController", urlPatterns = {"/SiteController"})
public class SiteController extends HttpServlet {


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
        RequestDispatcher rd;
        DbConnection conn = new DbConnection();
        VacantDao vacantDao = new VacantDao(conn);
        List<Vacant> list = vacantDao.getLasts();
        conn.disconnect();
        request.setAttribute("lasts", list);
        rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
        
    }

}
