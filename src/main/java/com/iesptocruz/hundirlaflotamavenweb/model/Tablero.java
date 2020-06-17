/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesptocruz.hundirlaflotamavenweb.model;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author tote
 */
public class Tablero {

    public enum Direccion {
        IZQUIERDA,
        DERECHA,
        ARRIBA,
        ABAJO
    }

    private Barco[] barcos;
    private Casilla[][] tablero;
    private int barcosActualIdx;
    private Direccion[] direcciones;
    private Direccion direccion;

    public Tablero(int barcos, int dim) {
        ini(barcos, dim);
        barcosActualIdx = 0;
    }

    public Tablero() {
        this(5, 10);
    }

    private void ini(int barcos, int dim) {
        tablero = new Casilla[dim][dim];
        this.barcos = new Barco[barcos];
        direcciones = Direccion.values();
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = new Casilla(new Punto(i, j), false);
            }
        }
        int longBarco=1;
        for (int i = 0; i < this.barcos.length; i++) {
            Barco b=null; 
            if(longBarco == 3 && i <= 3){
                b = new Barco(longBarco);
                this.barcos[i] = b;
                if(i == 3){
                    longBarco++;
                }
            }else{
                b = new Barco(longBarco++);
                this.barcos[i] = b;
            }
            
        }
    }

    public void ini() {
        for (int i = 0; i < barcos.length; i++) {
            barcoAleatorio(barcos[i]);
        }
    }

    private boolean isColisionX(Barco b) {
        boolean colision = false;
        //direccion = 1, 2, 3 ,4
        Casilla array[] = tablero[b.getHead().getPosY()];
        int i = b.getHead().getPosX();
        if ((b.getHead().getPosX() - array.length) > b.getSIZE()) {

            while (i < b.getHead().getPosX() + b.getSIZE() && !colision) {
                colision = array[i].getBarco() != null;
                i++;
            }
            if (!colision) {
                direccion = direcciones[1];
            }

        } else {

            while (i > b.getHead().getPosX() - b.getSIZE() && !colision) {
                colision = array[i].getBarco() != null;
                i--;
            }
            if (!colision) {
                direccion = direcciones[0];

            }
        }

        return colision;
    }

    private boolean isColisionY(Barco b) {
        boolean colision = false;
        int i = b.getHead().getPosY();
        int j = b.getHead().getPosX();
        if ((b.getHead().getPosY()) - tablero.length > b.getSIZE()) {
            while (i < b.getHead().getPosY() + tablero.length && !colision) {
                colision = tablero[i][j].getBarco() != null;
                i--;
            }
            if (!colision) {
                direccion = direcciones[2];
            }
        } else {
            while (i < b.getHead().getPosY() + b.getSIZE() && !colision) {
                colision = tablero[i][j].getBarco() != null;
                i++;
            }
            if (!colision) {
                direccion = direcciones[3];
            }
        }
        return colision;
    }

    private boolean dimensionarBarco(Barco b) {
        boolean ret = false;
        int i = b.getHead().getPosY();
        switch (direccion) {
            case IZQUIERDA:
                for (int j = b.getHead().getPosX(); j > (b.getHead().getPosX()
                        - b.getSIZE()); j--) {
                    tablero[i][j].setBarco(b);
                    ret = true;
                }
                break;
            case DERECHA:

                for (int j = b.getHead().getPosX(); j < (b.getHead().getPosX()
                        + b.getSIZE()); j++) {
                    tablero[i][j].setBarco(b);
                    ret = true;
                }
                break;
            case ARRIBA:
                for (int j = b.getHead().getPosX(); i < (b.getHead().getPosY()
                        + b.getSIZE()); i++) {
                    tablero[i][j].setBarco(b);
                    ret = true;
                }
                break;
            case ABAJO:
                for (int j = b.getHead().getPosX(); i < (b.getHead().getPosY()
                        + b.getSIZE()); i++) {
                    tablero[i][j].setBarco(b);
                    ret = true;
                }
                break;
            default:
        }

        return ret;
    }

    public void barcoAleatorio(Barco b) {
        Random rnd = new Random();
        boolean esInsersion = false;
        while (!esInsersion) {
            int headPosX = rnd.nextInt(tablero.length);
            int headPosY = rnd.nextInt(tablero[0].length);
            int direccionInt = 0;
            if (tablero[headPosX][headPosY].getBarco() == null) {
                b.setHead(tablero[headPosX][headPosY].getPunto());
                //tablero[headPosX][headPosY].setBarco(b);
                if (b.getSIZE() > 1) {
                    while (esOcupado(direccionInt, b) && direccionInt < 2) {
                        direccionInt++;
                    }
                    int limInf;
                    int limSup;
                    int pos;
                    switch (direccionInt) {
                        case 0:
                            limInf = b.getHead().getPosY();
                            limSup = limInf + b.getSIZE();
                            pos = b.getHead().getPosX();
                            if (limSup < tablero[pos].length) {
                                for (int i = limInf; i < limSup; i++) {
                                    tablero[pos][i].setBarco(b);
                                }
                                esInsersion = true;
                            }
                            break;
                        case 1:
                            limInf = b.getHead().getPosY();
                            limSup = limInf + b.getSIZE();
                            pos = b.getHead().getPosX();
                            if (limInf > 0) {
                                for (int i = limSup; i < limInf; i--) {
                                    tablero[pos][i].setBarco(b);
                                }
                                esInsersion = true;
                            }
                            break;
                    }
                } else {
                    tablero[b.getHead().getPosX()][b.getHead().getPosY()]
                            .setBarco(b);
                    esInsersion = true;
                }
            }
        }
    }

    public Casilla[][] get() {
        return tablero;
    }

    private boolean esOcupado(int direccion, Barco b) {
        int i;
        int pos, limiteInf, limiteSup;
        boolean ret = false;
        switch (direccion) {
            case 0:
                pos = b.getHead().getPosX();
                limiteInf = b.getHead().getPosY();
                limiteSup = limiteInf + b.getSIZE();
                i = limiteInf + 1;
                if (limiteSup < tablero[pos].length) {
                    while (i < limiteSup && !ret) {
                        ret = tablero[pos][i].getBarco() != null;
                        i++;
                    }
                }
                break;
            case 1:
                pos = b.getHead().getPosX();
                limiteInf = b.getHead().getPosY() + b.getSIZE();
                limiteSup = b.getHead().getPosY();
                i = limiteInf - 1;
                if (limiteInf >= 0) {
                    while (i > limiteSup && !ret) {
                        ret = tablero[pos][i].getBarco() != null;
                        i--;
                    }
                }
                break;
        }
        return ret;
    }

    public boolean add(Barco b) {
        boolean add = false;
        if (barcos[barcos.length - 1] == null) {
            barcos[barcosActualIdx] = b;
            barcosActualIdx++;
            if (isColisionX(b)) {
                if (isColisionY(b)) {
                    add = false;
                } else {
                    add = dimensionarBarco(b);
                }
            } else {
                add = dimensionarBarco(b);
            }

        }
        return add;
    }

    public void autoAdd(int size) {
        boolean agregar = false;
        Barco b = new Barco(size);
        Random rnd = new Random();
        b.setHead(new Punto(rnd.nextInt(tablero[0].length),
                rnd.nextInt(tablero.length)));
        while (!agregar) {
            agregar = add(b);
            if (!agregar) {
                b.getHead().setPosX(rnd.nextInt(tablero[0].length));
                b.getHead().setPosY(rnd.nextInt(tablero.length));
            }
        }

    }

    public String mostrarTablero() {
        String tableroStr = "";
        for (int i = 0; i < this.tablero.length; i++) {
            for (int j = 0; j < this.tablero[i].length; j++) {

                if (this.tablero[i][j].getBarco() == null) {
                    tableroStr += "~\t";
                } else if (!this.tablero[i][j].isActiva()) {

                    tableroStr += "0\t";

                } else {
                    tableroStr += this.tablero[i][j].getBarco().getSIZE() + "\t";
                }

            }
            tableroStr += "\n\n";
        }
        return tableroStr;
    }

    public Casilla getCasilla(int x, int y) {
        return tablero[y][x];
    }

    public int getSize() {
        return tablero.length;
    }

    public int getSize(int index) {
        return tablero[index].length;
    }
}
