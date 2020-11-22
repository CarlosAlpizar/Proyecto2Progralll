package com.progra.proyecto2.entidades;

import java.awt.Rectangle;
import java.util.Vector;

public class Jugador extends Tanque {

    /**
     * @return the reg
     */
    public Rectangle getReg() {
        return reg;
    }

    /**
     * @param reg the reg to set
     */
    public void setReg(Rectangle reg) {
        this.reg = reg;
    }
    //to save how many alive bullets shoot by player

    private Vector<Bala> balasJugador = new Vector<Bala>();
    Bala balaJugador = null;
    private Rectangle reg;

    public Jugador(int x, int y) {
        super(x, y);

        reg = new Rectangle(x, y, 32, 32);
    }

    //these function will move player tank in direction on speed
    public void moveUp() {
        y -= speed;

    }

    public void moveLeft() {
        x -= speed;
    }

    public void moveDown() {
        y += speed;
    }

    public void moveRight() {
        x += speed;
    }

    //shoot the bullets
    public void shoot() {
        if (this.getIsLive()) {

        }
    }

    public Vector<Bala> getBalasJugador() {
        return balasJugador;
    }

    public Bala getBalaJugador() {

        return balaJugador;
    }

}
