/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleatoritest;

import com.aleatoritest.dao.Pregunta;
import com.aleatoritest.dao.Prueba;
import com.aleatoritest.dto.JoinTable;
import com.aleatoritest.dto.JoinTableDriver;
import com.aleatoritest.dto.PreguntaDriver;
import com.aleatoritest.dto.PruebaDriver;
import com.aleatoritest.dto.UsuarioDriver;
import com.aleatoritest.dto.driver.DaoDriver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MauricioGabriel
 */
public class Grabar extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        String tabla = request.getParameter("tabla");
        DaoDriver driver = selectDriver(tabla);
        String id = request.getParameter("id");
        boolean nuevo = id.equals("");
        Object entidad = nuevo ? selectTable(tabla) : driver.buscarId(Integer.parseInt(id));
        request = makeTable(tabla, entidad, request, driver, nuevo);
        response.sendRedirect("home");
    }

    private DaoDriver selectDriver(String table) {
        switch (table) {
            case "preg":
                return new PreguntaDriver();
            case "prueba":
                return new PruebaDriver();
            default:
                return null;
        }
    }

    private Object selectTable(String table) {
        switch (table) {
            case "preg":
                return new Pregunta();
            case "prueba":
                return new Prueba();
            default:
                return null;
        }
    }

    private HttpServletRequest makeTable(String tabla, Object entidad,
            HttpServletRequest request, DaoDriver driver, boolean nuevo) {
        switch (tabla) {
            case "preg":
                Pregunta preg = (Pregunta) entidad;
                preg.setPregunta(request.getParameter("pregunta").trim());
                preg.setRespuestaCorrecta(request.getParameter("correcta"));
                preg.setAlternativa1(request.getParameter("alt1"));
                System.out.println(preg.getAlternativa1());
                preg.setAlternativa2(request.getParameter("alt2"));
                System.out.println(preg.getAlternativa2());
                preg.setAlternativa3(request.getParameter("alt3"));
                System.out.println(preg.getAlternativa3());
                preg.setEsVisible(request.getParameter("visible") != null);
                preg.setUsuario(new UsuarioDriver().buscarId((int) request.getSession(false).getAttribute("userId")));
                if (nuevo) {
                    preg.setFecha(new java.util.Date());
                }
                JoinTableDriver jtd = new JoinTableDriver(JoinTable.PREGUNTAHASMATERIA);
                int matId = Integer.parseInt(request.getParameter("materia").split(" - ")[0]);
                if (!nuevo) {
                    jtd.borrar(preg.getPreguntaId(), preg.getMateriaIds().get(0));
                }
                preg.setMateriaIds(new ArrayList<>(Arrays.asList(matId)));
                int prid;
                if (nuevo) {
                    prid = driver.guardar(preg);
                } else {
                    driver.editar(preg);
                    prid = preg.getPreguntaId();
                }
                jtd.guardar(prid, matId);
                break;
            case "prueba":
                Prueba prueba = (Prueba) entidad;
                prueba.setNombre(request.getParameter("nombre").trim());
                jtd = new JoinTableDriver(JoinTable.PRUEBAHASPREGUNTA);
                if (!nuevo) {
                    jtd.borrar(true, prueba.getPruebaId());
                }
                ArrayList<Integer> pregIds = new ArrayList<>();
                String[] params = request.getParameterValues("preguntas");
                for (String param : params) {
                    String pId = param.split(" - ")[0];
                    pregIds.add(Integer.parseInt(pId));
                }
                prueba.setCantidadPreguntas(params.length);
                prueba.setPreguntaIds(pregIds);
                matId = Integer.parseInt(request.getParameter("materia").split(" - ")[0]);
                prueba.setMateria(new com.aleatoritest.dto.MateriaDriver().buscarId(matId));
                if (prueba.getFecha() == null) {
                    prueba.setFecha(new java.util.Date());
                }
                prueba.setUsuario(new UsuarioDriver().buscarId((int) request.getSession(false).getAttribute("userId")));
                int pid;
                if (nuevo) {
                    pid = driver.guardar(prueba);
                } else {
                    driver.editar(prueba);
                    pid = prueba.getPruebaId();
                }
                pregIds.forEach((pregId) -> {
                    jtd.guardar(pid, pregId);
                });
                break;
            default:
        }
        return request;
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
