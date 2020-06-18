/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesptocruz.hundirlaflotamavenweb.controllers;

import com.iesptocruz.hundirlaflotamavenweb.model.Jugador;
import com.iesptocruz.hundirlaflotamavenweb.model.Partida;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Francisco de asís Domínguez Iceta <francisco.dominguez@ies.ptocruz>
 */
public class Procesar extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Procesar</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Estas en el get de Procesar</h1>");
            Partida p = (Partida)session.getAttribute("partida");
            if(p.getEstado().equals(Partida.Estado.ACTIVA)){
                request.getRequestDispatcher("partida.jsp")
                        .forward(request, response);
            }else if(p.getEstado().equals(Partida.Estado.VICTORIA)){
                request.getRequestDispatcher("victoria.jsp")
                        .forward(request, response);
            }
            out.print(p.getEstado());
            out.println("</body>");
            out.println("</html>");
        }
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            String nombre =request.getParameter("nombre");
            String accion = request.getParameter("accion");
            String modo = request.getParameter("modo");
            
            if(modo.equals("pve")){
                Partida p = new Partida(2, Partida.Modo.PVE);
                Jugador humano = new Jugador(nombre, Jugador.Tipo.HUMANO, p
                        , Partida.Turno.JUGADOR1);
                humano.setIdSesion(session.getId());
                
                Jugador maquina = new Jugador("Teddy", Jugador.Tipo.MAQUINA, p
                        , Partida.Turno.JUGADOR2);
                String botId = "bot_"+maquina.getNombre().toLowerCase()+session
                        .getId();
                maquina.setIdSesion(botId);
                
                p.setId(humano.getNombre()+"_"+session.getId());
                p.getJugadores().put(humano.getIdSesion(), humano);
                p.getJugadores().put(maquina.getIdSesion(), maquina);
                session.setAttribute("nombre", nombre);
                session.setAttribute("botId", botId);
                session.setAttribute("partida", p);
                session.setAttribute("modo", modo);
                request.getRequestDispatcher("partida.jsp").forward(request
                        , response);
                
            }
            
            

        }
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
