package com.progra.proyecto2.entidades;

import com.progra.utils.SpriteSheet;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Vector;

import java.util.Random;

public class Enemigo extends Tanque implements Runnable {

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

    public Enemigo(int x, int y, SpriteSheet sprite) {
        super(x, y);
        this.x = x;
        this.y = y;

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
            mover();
        }
    }

    public void mover() {

        switch (direccion) {
            case "Norte":
                y = y + -10;
                pasos--;
                imagen = sprite.getSprite(1);
                break;
            case "Oeste":
                x = x - 10;
                imagen = sprite.getSprite(8);
                pasos--;
                break;
            case "Sur":
                y = y + -10;
                imagen = sprite.getSprite(10);
                pasos--;
                break;
            case "Este":
                x = x + 10;
                imagen = sprite.getSprite(3);
                pasos--;
                break;
        }
    }

    public void pintar(Graphics g) {
        g.drawImage(imagen, x, y, 32, 32, null);
    }

}
