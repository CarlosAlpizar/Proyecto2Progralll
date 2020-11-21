package com.progra.proyecto2.entidades;

public class Nodo {
	int x;
	int y;
	String direccion;
	public Nodo(int x, int y, String direccion){
		this.x = x;
		this.y = y;
		this.direccion = direccion;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public String getDirection() {
		return direccion;
	}
}
