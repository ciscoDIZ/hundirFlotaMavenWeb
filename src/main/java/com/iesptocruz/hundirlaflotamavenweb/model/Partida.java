/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesptocruz.hundirlaflotamavenweb.model;

import java.util.HashMap;

/**
 *
 * @author tote
 */
public class Partida {

    public enum Turno{
        JUGADOR1,
        JUGADOR2,
    }
    
    public enum Modo{
        PVP,
        PVE
    }
    
    public enum Estado {
        ESPERA,
        PAUSADA,
        ACTIVA,
        VICTORIA
    }
    
    private String id;
    
    private Turno turno;
    private final Modo MODO;
    private Estado estado;
    private HashMap<String, Jugador> jugadores;
    private Casilla ultimoAcierto;
    private static boolean esPartidaGuardada=false;
    private static int estadosPartidaIdx=0;
    private static int idxJugadores = 0;

    public Partida(int jugadores, Modo modo) {
        turno = Turno.JUGADOR1;
        this.MODO = modo;
        estado = Estado.ESPERA; 
        this.jugadores = new HashMap<>();
        ultimoAcierto = null;
       
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void setJugador(String id, Jugador j) {
        jugadores.put(id, j);
    }

    public Jugador getJugador(String id){
        return jugadores.get(id);
    }
    
    public static void resetJugadorIdx(){
        idxJugadores = 0;
    }
    
    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Modo getMODO() {
        return MODO;
    }

    public HashMap<String, Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(HashMap<String, Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Casilla getUltimoAcierto() {
        return ultimoAcierto;
    }

    public void setUltimoAcierto(Casilla ultimoAcierto) {
        this.ultimoAcierto = ultimoAcierto;
    }

    public static boolean isEsPartidaGuardada() {
        return esPartidaGuardada;
    }

    public static void setEsPartidaGuardada(boolean esPartidaGuardada) {
        Partida.esPartidaGuardada = esPartidaGuardada;
    }

    public static int getEstadosPartidaIdx() {
        return estadosPartidaIdx;
    }

    public static void setEstadosPartidaIdx(int estadosPartidaIdx) {
        Partida.estadosPartidaIdx = estadosPartidaIdx;
    }

    public static int getIdxJugadores() {
        return idxJugadores;
    }

    public static void setIdxJugadores(int idxJugadores) {
        Partida.idxJugadores = idxJugadores;
    }
    
    
    /*public boolean disparo(int x, int y) {
        Casilla c = t.getCasilla(x, y);
        boolean acierto = false;
        if (c.isActiva()) {
            acierto = c.getBarco() != null;
            c.setActiva(false);
            if (acierto) {
                int vidas = c.getBarco().getVidas();
                c.getBarco().setVidas(vidas - 1);
            }
        }

        return acierto;
    }

    public boolean autoDisparo() {
        boolean ret = false;
        Random rnd = new Random();
        if (ultimoAcierto == null) {
            int posY = rnd.nextInt(t.getSize());
            int posX = rnd.nextInt(t.getSize(0));
            ret = disparo(posY, posX);
            if (ret) {
                ultimoAcierto = t.getCasilla(posX, posY);
            }
        } else {
            if (ultimoAcierto.getBarco().getSIZE() - ultimoAcierto.getBarco()
                    .getVidas() == 1) {
                if (ultimoAcierto.getPunto().getPosX() + 1 < t.getSize(0)
                        && t.getCasilla(ultimoAcierto.getPunto().getPosY(),
                                 ultimoAcierto.getPunto().getPosX() + 1)
                                .isActiva()) {
                    ret = disparo(ultimoAcierto.getPunto().getPosY(),
                             ultimoAcierto.getPunto().getPosX() + 1);
                    t.gepublic boolean disparo(int x, int y) {
        Casilla c = t.getCasilla(x, y);
        boolean acierto = false;
        if (c.isActiva()) {
            acierto = c.getBarco() != null;
            c.setActiva(false);
            if (acierto) {
                int vidas = c.getBarco().getVidas();
                c.getBarco().setVidas(vidas - 1);
            }
        }

        return acierto;
    }tCasilla(ultimoAcierto.getPunto().getPosY(),
                             ultimoAcierto.getPunto().getPosX() + 1)
                            .setActiva(false);
                    if (ret) {
                        ultimoAcierto = t.getCasilla(ultimoAcierto.getPunto()
                                .getPosY(), ultimoAcierto.getPunto()
                                        .getPosX() + 1);

                    }
                } else if (ultimoAcierto.getPunto().getPosX() - 1 >= 0
                        && t.getCasilla(ultimoAcierto.getPunto().getPosY(),
                                 ultimoAcierto.getPunto().getPosX() - 1)
                                .isActiva()) {
                    ret = disparo(ultimoAcierto.getPunto().getPosY(),
                             ultimoAcierto.getPunto().getPosX() - 1);
                    t.getCasilla(ultimoAcierto.getPunto().getPosY(),
                             ultimoAcierto.getPunto().getPosX() + 1)
                            .setActiva(false);
                    if (ret) {
                        ultimoAcierto = t.getCasilla(ultimoAcierto.getPunto()
                                .getPosY(), ultimoAcierto.getPunto()
                                        .getPosX() - 1);
                    }
                } else if (ultimoAcierto.getPunto().getPosY() + 1 < t.getSize()
                        && t.getCasilla(ultimoAcierto.getPunto().getPosY() + 1,
                                 ultimoAcierto.getPunto().getPosX())
                                .isActiva()) {
                    ret = disparo(ultimoAcierto.getPunto().getPosY() + 1,
                             ultimoAcierto.getPunto().getPosX());
                    t.getCasilla(ultimoAcierto.getPunto().getPosY() + 1,
                             ultimoAcierto.getPunto().getPosX())
                            .setActiva(false);
                    if (ret) {
                        ultimoAcierto = t.getCasilla(ultimoAcierto.getPunto()
                                .getPosY() + 1, ultimoAcierto.getPunto()
                                        .getPosX());
                    }
                } else if (ultimoAcierto.getPunto().getPosY() - 1 < t.getSize()
                        && t.getCasilla(ultimoAcierto.getPunto().getPosY() - 1,
                                 ultimoAcierto.getPunto().getPosX())
                                .isActiva()) {
                    ret = disparo(ultimoAcierto.getPunto().getPosY() - 1,
                             ultimoAcierto.getPunto().getPosX());
                    t.getCasilla(ultimoAcierto.getPunto().getPosY() + 1,
                             ultimoAcierto.getPunto().getPosX())
                            .setActiva(false);
                    if (ret) {
                        ultimoAcierto = t.getCasilla(ultimoAcierto.getPunto()
                                .getPosY() + 1, ultimoAcierto.getPunto()
                                        .getPosX());
                    }
                }
            }
        }
        return ret;
    }*/

    
}
