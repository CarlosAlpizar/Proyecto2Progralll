package com.progra.proyecto2.entidades;


public class Tanque {
	int x;
	int y;
	int speed = 3;
	String direccion = "Norte";
	String color;
	private boolean isLive = true;
	//constructor for tank
	public Tanque(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getDirection() {
		return direccion;
	}
	public void setDirection(String direction) {
		this.direccion = direction;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	public boolean getIsLive() {
		return isLive;
	}

	public void setIsLive(boolean isLive) {
		this.isLive = isLive;
	}
	
}
