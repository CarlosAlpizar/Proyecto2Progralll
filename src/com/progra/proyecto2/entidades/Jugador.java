package com.progra.proyecto2.entidades;

import java.awt.Rectangle;
import java.util.Vector;

public class Jugador extends Tanque {

    private Vector<Bala> balasJugador = new Vector<Bala>();
    Bala balaJugador = null;

    public Jugador(int x, int y) {
        super(x, y);

    }

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

    public void shoot(int x, int y, String direccion) {

        balaJugador = new Bala(x, y, direccion);
        balasJugador.add(balaJugador);

        switch (direccion) {
            case "Norte":
                y = y + 5;
                this.setX(x);
                this.setY(y);

                break;

            case "Oeste":

                x = x - 5;

                this.setX(x);
                this.setY(y);

                break;

            case "Sur":

                y = y + -5;

                this.setX(x);
                this.setY(y);
                break;

            case "Este":

                x = x + 5;
                this.setX(x);
                this.setY(y);

                break;
        }
    }

    public Vector<Bala> getBalasJugador() {
        return balasJugador;
    }

    public Bala getBalaJugador() {

        return balaJugador;
    }

}
