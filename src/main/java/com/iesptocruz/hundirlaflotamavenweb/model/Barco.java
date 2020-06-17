/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesptocruz.hundirlaflotamavenweb.model;

/**
 *
 * @author tote
 */
public class Barco {
    
    private final int  SIZE;
    private Punto head;
    private int vidas;
    private boolean hundido;
    
    public Barco(int size) {
        SIZE = size;
        vidas = size;
        head = null;
        hundido = false;
    }
    public Barco(int size, int x, int y) {
        SIZE = size;
        vidas = size;
        head = new Punto(x, y);
        hundido = false;
    }
    public int getSIZE() {
        return SIZE;
    }
    
    public Punto getHead() {
        return head;
    }

    public void setHead(Punto head) {
        this.head = head;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
        hundido = vidas == 0;
    }

    public boolean isHundido() {
        return hundido;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }

    @Override
    public String toString() {
        return ""+SIZE;
    }
    
    
}
