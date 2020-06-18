/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesptocruz.hundirlaflotamavenweb.model;

import java.util.Random;

/**
 *
 * @author Francisco de asís Domínguez Iceta <francisco.dominguez@ies.ptocruz>
 */
public class Jugador {

    public enum Tipo {
        HUMANO,
        MAQUINA
    }

    private String idSesion;
    private String nombre;
    private Tipo tipo;
    private Partida.Turno prioridadTurno;
    private Tablero tablero;
    private Integer puntuacion;
    private Partida partida;

    public Jugador() {
    }

    public Jugador(String nombre, Tipo tipo, Partida partida, Partida.Turno priodidadTurno) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.partida = partida;
        this.prioridadTurno = priodidadTurno;
        tablero = new Tablero(5, 10);
        tablero.ini();
        puntuacion = 0;
    }

    public boolean disparo(int x, int y) {
        Casilla c = null;
        boolean acierto = false;
        boolean victoria = false;
        if (partida.getEstado().equals(Partida.Estado.ACTIVA)) {
            if (partida.getTurno().equals(Partida.Turno.JUGADOR1) && prioridadTurno
                    .equals(Partida.Turno.JUGADOR1)) {
                for (Jugador jugador : partida.getJugadores().values()) {
                    if (jugador.getPrioridadTurno().equals(Partida.Turno.JUGADOR2)) {
                        c = partida.getJugadores().get(jugador.idSesion).getTablero()
                                .getCasilla(x, y);
                        victoria = partida.getJugador(jugador.idSesion).determinarVicotria();
                        if (victoria) {
                            partida.setEstado(Partida.Estado.VICTORIA);
                        }
                    }
                }
                if (!victoria) {
                    partida.setTurno(Partida.Turno.JUGADOR2);
                }
            } else if (partida.getTurno().equals(Partida.Turno.JUGADOR2)
                    && prioridadTurno.equals(Partida.Turno.JUGADOR2)) {
                for (Jugador jugador : partida.getJugadores().values()) {
                    if (jugador.getPrioridadTurno().equals(Partida.Turno.JUGADOR1)) {
                        c = partida.getJugadores().get(jugador.idSesion).getTablero()
                                .getCasilla(x, y);
                        victoria = partida.getJugador(jugador.idSesion).determinarVicotria();
                        if (victoria) {
                            partida.setEstado(Partida.Estado.VICTORIA);
                        }
                    }
                }
                if (!victoria) {
                    partida.setTurno(Partida.Turno.JUGADOR1);
                }
            }
            if (c != null && c.isActiva()) {
                acierto = c.getBarco() != null;
                c.setActiva(false);
                if (acierto) {
                    int vidas = c.getBarco().getVidas();
                    c.getBarco().setVidas(vidas - 1);

                }
            }
        }
        return acierto;
    }

    private boolean determinarVicotria() {
        boolean victoria = true;
        int i = 0;
        Barco[] barcos = tablero.getBarcos();
        while (victoria && i < barcos.length) {
            if (!barcos[i].isHundido()) {
                victoria = false;
            }
            i++;
        }
        return victoria;
    }

    public boolean disparo() {
        Random rnd = new Random();
        int x = rnd.nextInt(tablero.getSize());
        int y = rnd.nextInt(tablero.getSize());
        while (!partida.getJugador(idSesion).getTablero().getCasilla(x, y).isActiva()) {
            x = rnd.nextInt(tablero.getSize());
            y = rnd.nextInt(tablero.getSize());
        }
        return disparo(x, y);
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the tablero
     */
    public Tablero getTablero() {
        return tablero;
    }

    /**
     * @param tablero the tablero to set
     */
    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    /**
     * @return the puntuacion
     */
    public Integer getPuntuacion() {
        return puntuacion;
    }

    /**
     * @param puntuacion the puntuacion to set
     */
    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Partida.Turno isPrioridadTurno() {
        return prioridadTurno;
    }

    public void setPrioridadTurno(Partida.Turno prioridadTurno) {
        this.prioridadTurno = prioridadTurno;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public Partida.Turno getPrioridadTurno() {
        return prioridadTurno;
    }

}
