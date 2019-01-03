/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleatoritest;

import com.aleatoritest.dao.Pregunta;
import com.aleatoritest.dao.Usuario;
import com.aleatoritest.dto.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MauricioGabriel
 */
public class Home extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        boolean active = request.getSession(false) != null;
        if (active) {
            UsuarioDriver ud = new UsuarioDriver();
            int userId = (int) request.getSession().getAttribute("userId");
            Usuario user = prepareUser(ud.buscarId(userId));
            ArrayList<Integer> ocupadas = new JoinTableDriver(JoinTable.PREGUNTACOMPARTIDA).listar(false, userId);
            request.setAttribute("ocupadas", ocupadas);
            request.setAttribute("user", user);
            request.setAttribute("compartibles", listarPreguntas(userId));
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
    
    private Usuario prepareUser(Usuario user) {
        user.makePreguntaList();
        user.makePruebaList();
        user.makeUsuarioList();
        user.getPreguntaList().forEach((pregunta) -> pregunta.makeMateriaList());
        return user;
    }
    private ArrayList<Pregunta> listarPreguntas(int userId) {
        ArrayList<Pregunta> pregs = new ArrayList<>();
        new PreguntaDriver().listar().stream().filter((pregunta) -> (pregunta.getEsVisible() && pregunta.getUsuario().getUsuarioId() != userId)).forEachOrdered((pregunta) -> {
            pregs.add(pregunta);
        });
        pregs.forEach((preg) -> {preg.makeMateriaList();});
        return pregs;
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}
