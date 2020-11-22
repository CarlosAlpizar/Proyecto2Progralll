package com.progra.proyecto2.entidades;

import com.progra.utils.SpriteSheet;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Vector;

import java.util.Random;

public class Enemigo extends Tanque implements Runnable {

    /**
     * @return the aux
     */
    public boolean isAux() {
        return aux;
    }

    /**
     * @param aux the aux to set
     */
    public void setAux(boolean aux) {
        this.aux = aux;
    }

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
    private boolean aux;
    int bulletTimeInterval;
    Vector<Enemigo> enemies = new Vector<Enemigo>();
    private Vector<Bala> enemyBullets = new Vector<Bala>();
    int select;
    String[] direccionSet = {"Norte", "Oeste", "Sur", "Este"};
    Random random = new Random();
    int x, y;
    SpriteSheet sprite;
    BufferedImage imagen = null;
    int pasos = 20;
    private Rectangle reg = null;

    public Enemigo(int x, int y, SpriteSheet sprite) {
        super(x, y);
        this.x = x;
        this.y = y;
        reg = new Rectangle(x, y, 32, 32);

        this.sprite = sprite;
        imagen = sprite.getSprite(1);

    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            if (pasos == 0) {
                select = random.nextInt(direccionSet.length);
                this.direccion = direccionSet[select];
                pasos = 20;
            }
            if (this.getIsLive() == false) {
                break;

            }

            mover();

        }
    }

    public void mover() {

        switch (direccion) {
            case "Norte":
                if (pasos != 0) {
                    y = y + 5;
                    pasos--;
                    this.setX(x);
                    this.setY(y);

                }

                break;
            case "Oeste":
                if (pasos != 0) {
                    x = x - 5;
                    pasos--;
                    this.setX(x);
                    this.setY(y);
                }
                break;
            case "Sur":
                if (pasos != 0) {
                    y = y + -5;
                    pasos--;
                    this.setX(x);
                    this.setY(y);
                }

                break;
            case "Este":
                if (pasos != 0) {
                    x = x + 5;
                    pasos--;
                    this.setX(x);
                    this.setY(y);
                }
                break;

        }
    }

}
