/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesptocruz.hundirlaflotamavenweb.model;

/**
 *
 * @author Alex
 */
public class Casilla {

    private Punto punto;
    private boolean activa;
    private Barco b;

    public Casilla() {
    }

    
    
    public Casilla(Punto punto, boolean ocupada) {
        this.punto = punto;
        this.activa = true;
    }

    public Casilla(Punto punto) {
        this.punto = punto;
        activa = true;
    }

    public Punto getPunto() {
        return punto;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setPunto(Punto punto) {
        this.punto = punto;
    }

    public Barco getBarco() {
        return b;
    }

    public void setBarco(Barco b) {
        this.b = b;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

}
