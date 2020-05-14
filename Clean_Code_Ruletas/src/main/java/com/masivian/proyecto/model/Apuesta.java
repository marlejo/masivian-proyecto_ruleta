package com.masivian.proyecto.model;

import java.io.Serializable;

public class Apuesta implements Serializable{
	private String id;
	private Ruleta ruleta;
	private Usuario usuario;
	private int creditosApuesta;
	private String numeroApuesta;
	private int resultadoApuesta;
	private Boolean estadoApuesta;
	
	public Apuesta(String id, Ruleta ruleta, Usuario usuario, int creditosApuesta, String numeroApuesta, int resultadoApuesta, Boolean estadoApuesta) {
		super();
		this.id = id;
		this.ruleta = ruleta;
		this.usuario = usuario;
		this.creditosApuesta = creditosApuesta;
		this.numeroApuesta = numeroApuesta;
		this.resultadoApuesta = resultadoApuesta;
		this.estadoApuesta = estadoApuesta;
	}
	
	public Apuesta(String id, Ruleta ruleta, Usuario usuario, int creditosApuesta, String numeroApuesta, Boolean estadoApuesta) {
		super();
		this.id = id;
		this.ruleta = ruleta;
		this.usuario = usuario;
		this.creditosApuesta = creditosApuesta;
		this.numeroApuesta = numeroApuesta;
		this.estadoApuesta = estadoApuesta;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Ruleta getRuleta() {
		return ruleta;
	}

	public void setRuleta(Ruleta ruleta) {
		this.ruleta = ruleta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getResultadoApuesta() {
		return resultadoApuesta;
	}

	public void setResultadoApuesta(int resultadoApuesta) {
		this.resultadoApuesta = resultadoApuesta;
	}

	public int getCreditosApuesta() {
		return creditosApuesta;
	}

	public void setCreditosApuesta(int creditosApuesta) {
		this.creditosApuesta = creditosApuesta;
	}

	public String getNumeroApuesta() {
		return numeroApuesta;
	}

	public void setNumeroApuesta(String numeroApuesta) {
		this.numeroApuesta = numeroApuesta;
	}

	public Boolean getEstadoApuesta() {
		return estadoApuesta;
	}

	public void setEstadoApuesta(Boolean estadoApuesta) {
		this.estadoApuesta = estadoApuesta;
	}		
}
