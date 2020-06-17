<%-- 
    Document   : partida
    Created on : 17-jun-2020, 10:26:40
    Author     : Francisco de asís Domínguez Iceta <francisco.dominguez@ies.ptocruz>
--%>

<%@page import="com.iesptocruz.hundirlaflotamavenweb.model.Casilla"%>
<%@page import="com.iesptocruz.hundirlaflotamavenweb.model.Jugador"%>
<%@page import="com.iesptocruz.hundirlaflotamavenweb.model.Jugador"%>
<%@page import="com.iesptocruz.hundirlaflotamavenweb.model.Partida"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css">
        <title>Partida</title>
    </head>
    <body>
        <h1>partida</h1>
        <div class='tableros'>

            <div id='tableroMaquina' class='grid'>
                <%
                    Partida p = (Partida) session.getAttribute("partida");
                   
                    if(p.getEstado().equals(Partida.Estado.ESPERA)){
                        p.setEstado(Partida.Estado.ACTIVA);
                    }
                    Casilla[][] casillasBot = null;
                    for (Jugador value : p.getJugadores().values()) {
                        if (value.getIdSesion().contains("bot")) {
                            casillasBot = value.getTablero().get();
                        }
                    }
                    for (Casilla[] casilla : casillasBot) {
                        for (Casilla casilla1 : casilla) {
                            String x = String.valueOf(casilla1.getPunto().getPosX());
                            String y = String.valueOf(casilla1.getPunto().getPosY());
                           
                            out.print("<a href=\"gestorPartida?x="+x+"&y="+y+"\"><div class='grid-item'></div></a>");
                        }
                    }

                    session.setAttribute("partida", p);
                %>
            </div>
        </div>

        <div class="tableros">
            <div id='tableroHumano' class='grid'>
                <%                    
                    if (p.getMODO().equals(Partida.Modo.PVE)) {
                        for (Casilla[] casillas : p.getJugador(session.getId()).getTablero().get()) {
                            for (Casilla casilla : casillas) {
                                String casillaStr = (casilla.getBarco() != null)
                                        ? casilla.getBarco().toString() : "";
                                out.print("<div class='grid-item'>" + casillaStr + "</div>");
                            }
                        }
                    }
                %>
            </div>
        </div>



    </body>
</html>
