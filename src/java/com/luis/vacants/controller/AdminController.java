/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luis.vacants.controller;

import com.luis.vacants.dao.DbConnection;
import com.luis.vacants.dao.UserDao;
import com.luis.vacants.dao.VacantDao;
import com.luis.vacants.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luis
 */
@WebServlet(name = "AdminController", urlPatterns = {"/AdminController"})
public class AdminController extends HttpServlet {
    
    
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
        //El paramtro accion recibido del archivo jsp se usa para saber que accion se ejecuta
        String action= request.getParameter("action");
        
        //Recpero la sesion activa que viene con el request
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        String msg = "";
        
        switch(action){
            case "login":
                //Si el usuario no esta logueado, lo mando al form loguin
                if(session.getAttribute("user") == null){
                    request.setAttribute("message", msg);
                    rd = request.getRequestDispatcher("/form_login.jsp");
                    rd.forward(request, response);
                }else{
                    rd = request.getRequestDispatcher("/admin.jsp");
                    rd.forward(request, response);
                }
                break;
            case "logout":
                    session.invalidate();
                    response.sendRedirect(request.getContextPath() + "/SiteController");
                break;
            case "create":
                rd = request.getRequestDispatcher("/form_vacant.jsp");
                rd.forward(request, response);
                break;
            case "delete":
                if(session.getAttribute("user") == null){
                    msg = "Accceso denegado";
                    request.setAttribute("message", msg);
                    rd = request.getRequestDispatcher("/form_login.jsp");
                    rd.forward(request, response);
                }else{
                    this.deleteVacant(request,response);
                }
                break;
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String msg = "";
        
        //Los objetos sesion estan disponibles durante en todas la paginas de la web
        HttpSession session = request.getSession();
        
        DbConnection conn = new DbConnection();
        UserDao userDao = new UserDao(conn);
        //Verifico si el usuario es correcto
        User user = userDao.login(request.getParameter("email"), request.getParameter("pass"));
        conn.disconnect();
        
        RequestDispatcher rd;
        if(user.getEmail() != null){
            //La variable de sesion toma los datos del usuario
            //Verificar en el administrador de aplicaciones de tomcat
            session.setAttribute("user", user);
            rd = request.getRequestDispatcher("/admin.jsp");
            rd.forward(request, response);
        }else{
            msg = "Mail o passwords incorrectos";
            request.setAttribute("message", msg);
            rd = request.getRequestDispatcher("/form_login.jsp");
            rd.forward(request, response);
        }
    }

    private void deleteVacant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DbConnection conn = new DbConnection();
        VacantDao vacantDao = new VacantDao(conn);
        int result = vacantDao.deleteById(Integer.parseInt(request.getParameter("id")));
        String msg = "";
        
        if(result == 1)
            msg = "Vacante eliminada correctamente";
        else
            msg = "Ocurrio un error. No se ha eliminado la vacante";
        
        conn.disconnect();
        request.setAttribute("message", msg);
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/result.jsp");
        rd.forward(request, response);
        
    }

}
