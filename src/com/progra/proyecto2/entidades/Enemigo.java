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
    int pasos = 10;
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
                pasos = 10;
            }
            if (this.getIsLive() == false) {
                break;

            }
            if (aux != true) {
                mover();
            }

        }
    }

    public void mover() {

        switch (direccion) {
            case "Norte":
                //if (aux == true) {
                y = y + -10;
                pasos--;
                imagen = sprite.getSprite(1);
                reg.setBounds(x, y, 32, 32);
                // } else {
                //  System.out.println("no se debe mover");
                //}
                break;
            /*case "Oeste":
                if (aux == true) {
                    x = x - 10;
                    imagen = sprite.getSprite(8);
                    pasos--;
                    reg.setBounds(x, y, 32, 32);
                } else {
                    System.out.println("no se debe mover");
                }
                break;
            case "Sur":
                if (aux == true) {
                    y = y + -10;
                    imagen = sprite.getSprite(10);
                    pasos--;
                    reg.setBounds(x, y, 32, 32);
                } else {
                    System.out.println("no se debe mover");
                }
                break;
            case "Este":
                if (aux == true) {
                    x = x + 10;
                    imagen = sprite.getSprite(3);
                    pasos--;
                    reg.setBounds(x, y, 32, 32);
                } else {
                    System.out.println("no se debe mover");
                }
                break;
             */
        }
    }

    public void pintar(Graphics g) {
        g.drawImage(imagen, x, y, 32, 32, null);
    }

}
