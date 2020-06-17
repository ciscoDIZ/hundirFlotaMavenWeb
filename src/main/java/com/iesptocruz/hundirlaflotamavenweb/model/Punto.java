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
public class Punto {
    private int posX;
    private int posY;
    
    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Punto() {
    }
    
    public Punto(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }
    
    
}
