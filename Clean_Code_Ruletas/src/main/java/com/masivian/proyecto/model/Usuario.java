package com.masivian.proyecto.model;

import java.io.Serializable;

public class Usuario implements Serializable{
	private String id;
	private int creditos;
	private String nombre;
	
	public Usuario(String id, String nombre, int creditos ) {
		super();
		this.id = id;
		this.creditos = creditos;
		this.nombre = nombre;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCreditos() {
		return creditos;
	}
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
