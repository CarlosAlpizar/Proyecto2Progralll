package com.progra.proyecto2.entidades;

import java.awt.Rectangle;
import java.util.Vector;

public class Jugador extends Tanque {

    private Vector<Bala> balasJugador = new Vector<Bala>();
    Bala balaJugador = new Bala(x, y, direccion);

    private int xb = balaJugador.getBulletX();
    private int yb = balaJugador.getBulletY();

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

    public Vector<Bala> getBalasJugador() {
        return balasJugador;
    }

    public Bala getBalaJugador() {

        return balaJugador;
    }

    public void disparo() {
        System.out.println("piuu piuu");
        switch (direccion) {
            case "Norte":

                yb = yb + 5;
                for (int i = 0; i < 100; i++) {
                    balaJugador.setBulletX(xb);
                    balaJugador.setBulletY(yb);
                }
                break;

            case "Oeste":

                xb = xb - 5;
                for (int i = 0; i < 100; i++) {
                    balaJugador.setBulletX(xb);
                    balaJugador.setBulletY(yb);
                }
                break;

            case "Sur":

                yb = yb - 5;
                for (int i = 0; i < 100; i++) {
                    balaJugador.setBulletX(xb);
                    balaJugador.setBulletY(yb);
                }
                break;

            case "Este":

                xb = xb + 5;
                for (int i = 0; i < 100; i++) {
                    balaJugador.setBulletX(xb);
                    balaJugador.setBulletY(yb);
                }
                break;
        }
        Thread hilo = new Thread(balaJugador);
        hilo.start();
    }
}
