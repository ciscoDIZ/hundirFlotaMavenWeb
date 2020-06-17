/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesptocruz.hundirlaflotamavenweb.model;

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
    private boolean prioridadTurno;
    private Tablero tablero;
    private Integer puntuacion;
    public Jugador(String nombre, Tipo tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
        prioridadTurno = false;
        tablero = new Tablero(5, 10);
        tablero.ini();
        puntuacion = 0;
    }

    public boolean disparo(int x, int y, Tablero t) {
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

    public boolean isPrioridadTurno() {
        return prioridadTurno;
    }

    public void setPrioridadTurno(boolean prioridadTurno) {
        this.prioridadTurno = prioridadTurno;
    }
    
}
