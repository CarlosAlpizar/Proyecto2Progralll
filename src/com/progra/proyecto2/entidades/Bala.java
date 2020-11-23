package com.progra.proyecto2.entidades;

public class Bala implements Runnable {

    public void setBulletX(int bulletX) {
        this.bulletX = bulletX;
    }

    public void setBulletY(int bulletY) {
        this.bulletY = bulletY;
    }

    private int bulletX;
    private int bulletY;
    String direccionBala;
    int velocidadBala = 4;
    boolean isLive = true;

    public Bala(int x, int y, String direccion) {
        this.bulletX = x;
        this.bulletY = y;
        this.direccionBala = direccion;
    }

    public int getBulletX() {
        return bulletX;
    }

    public int getBulletY() {
        return bulletY;
    }

    public boolean getIsLive() {
        return isLive;
    }

    public void setIsLive(boolean isLive) {
        this.isLive = isLive;
    }

    @Override
    public void run() {

        while (true) {

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            shoot(direccionBala);
        }
    }

    public void shoot(String direccion) {
        switch (direccion) {
            case "Norte":

                bulletY = bulletY + 5;

                this.setBulletX(bulletX);
                this.setBulletY(bulletY);

                break;

            case "Oeste":

                bulletX = bulletX - 5;

                this.setBulletX(bulletX);
                this.setBulletY(bulletY);

                break;

            case "Sur":

                bulletY = bulletY + -5;

                this.setBulletX(bulletX);
                this.setBulletY(bulletY);

                break;

            case "Este":
                bulletX = bulletX + 5;
                this.setBulletX(bulletX);
                this.setBulletY(bulletY);
                break;
        }
    }

}
