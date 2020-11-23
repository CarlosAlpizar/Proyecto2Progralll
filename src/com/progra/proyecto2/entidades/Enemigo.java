package com.progra.proyecto2.entidades;

import com.progra.utils.SpriteSheet;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Vector;

import java.util.Random;

public class Enemigo extends Tanque implements Runnable {

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

    private Rectangle borde = new Rectangle(50, 50, 300, 300);

    private boolean putavida = false;

    public Enemigo(int x, int y, SpriteSheet sprite) {
        super(x, y);
        this.x = x;
        this.y = y;
        reg = new Rectangle(x, y, 32, 32);

        this.sprite = sprite;
        imagen = sprite.getSprite(1);
    }

    public boolean isAux() {
        return aux;
    }

    public void setAux(boolean aux) {
        this.aux = aux;
    }

    public SpriteSheet getSprite() {
        return sprite;
    }

    public Rectangle getReg() {
        return reg;
    }

    public void setReg(Rectangle reg) {
        this.reg = reg;
    }

    public void cambiarDireccion() {
        select = random.nextInt(direccionSet.length);
        this.direccion = direccionSet[select];
    }

    public void cambiarDireccion2() {
        if ("Norte".equals(direccion)) {
            this.direccion = "Sur";
        } else if ("Sur".equals(direccion)) {
            this.direccion = "Norte";
        } else if ("Este".equals(direccion)) {
            this.direccion = "Oeste";
        } else if ("Oeste".equals(direccion)) {
            this.direccion = "Este";
        }
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
                cambiarDireccion();
                pasos = 20;
            }

            if (colision()) {
                cambiarDireccion2();
            }
            mover();

        }
    }

    public boolean colision() {
        if (reg.x <= 0) {
            return true;
        } else if (reg.y <= 0) {
            return true;
        } else if ((reg.y + 32) > 530) {
            return true;
        } else if ((reg.x + 32) > 485) {
            return true;
        }
        return false;
    }

//0, 0, 580, 535
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
