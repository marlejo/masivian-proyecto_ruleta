package com.masivian.proyecto.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.masivian.proyecto.model.Apuesta;

public interface ApuestaRepository {
	String saveApuesta(Apuesta apuesta);
	
	Map<String,Apuesta> findAllApuestas();
	
	Apuesta findApuestaById(String id);
	
	String updateApuesta (Apuesta apuesta);
	
	String deleteApuesta(String id);
	
	Boolean apuestaExist(String id);
	
	ArrayList<Apuesta> findAllApuestasByRuletaId(String idRuleta);

}
