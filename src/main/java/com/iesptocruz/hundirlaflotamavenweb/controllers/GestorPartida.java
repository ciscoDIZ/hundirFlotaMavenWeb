/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesptocruz.hundirlaflotamavenweb.controllers;

import com.iesptocruz.hundirlaflotamavenweb.model.Casilla;
import com.iesptocruz.hundirlaflotamavenweb.model.Jugador;
import com.iesptocruz.hundirlaflotamavenweb.model.Partida;
import com.iesptocruz.hundirlaflotamavenweb.model.Tablero;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Francisco de asís Domínguez Iceta <francisco.dominguez@ies.ptocruz>
 */
public class GestorPartida extends HttpServlet {

    private static HashMap<String, Partida> partidas = new HashMap<>();

    public static HashMap<String, Partida> getPartidas() {
        return partidas;
    }

    public static void setPartidas(HashMap<String, Partida> partidas) {
        GestorPartida.partidas = partidas;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
            HttpSession session = request.getSession();
            String botId = (String) session.getAttribute("botId");
            String x = request.getParameter("x");
            String y = request.getParameter("y");
            out.print("disparo: x(" + x + ") y(" + y + ")");
            Partida p = (Partida) session.getAttribute("partida");
            if (p.getMODO().equals(Partida.Modo.PVE)) {
                Jugador humano = p.getJugador(session.getId());
                Jugador maquina = p.getJugador(botId);
            }
            /*out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GestorPartidas</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GestorPartidas at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
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
            String title = "";
            String h1Saludo = "Hundir la flota";
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>" + title + "</title>");
            //out.print("<meta http-equiv='refresh' content='1' >");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + h1Saludo + "</h1>");
            String accion = (String) session.getAttribute("accion");
            Partida partida = (Partida) session.getAttribute("partida");
            out.print(partida.getMODO());
            if (partida.getMODO().equals(Partida.Modo.PVE)) {
                Tablero tableroHumano = partida.getJugadores().get(session.getId()).getTablero();
                out.print("<div id='tableroHumano' class='grid'>");
                for (Casilla[] casillas : tableroHumano.get()) {
                    for (Casilla casilla : casillas) {
                        String casillaStr = (casilla.getBarco() != null) ? casilla.getBarco()
                                .toString() : "";
                        out.print("<div style='display: inline-block; border: solid black 1px '>" + casillaStr + "</div>");
                    }
                    out.print("<br>");
                }
            }
            session.setAttribute("partida", partida);
            out.println("</body>");
            out.println("</html>");
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
