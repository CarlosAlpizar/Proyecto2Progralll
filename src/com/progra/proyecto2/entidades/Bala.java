package com.progra.proyecto2.entidades;

public class Bala implements Runnable{
	int bulletX;
	int bulletY;
	String direccionBala;
	int velocidadBala=4;
	boolean isLive = true;
	
	public Bala(int x, int y, String direccion){
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
		// TODO Auto-generated method stub
		while(true){
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
}
