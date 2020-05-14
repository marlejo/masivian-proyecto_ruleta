package com.masivian.proyecto.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public class Ruleta implements Serializable{
	
	private String id;
	private Boolean estado;
	
	public Ruleta(String id, Boolean estado) {
		super();
		this.id = id;
		this.estado = estado;
	}
	
	public Ruleta(Boolean estado) {
		super();
		this.estado = estado;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
}
