package com.masivian.proyecto.repositories;

import java.util.Map;

import com.masivian.proyecto.model.Ruleta;

public interface RuletaRepository {
	
	String saveRuleta(Ruleta ruleta);
	
	Map<String,Ruleta> findAllRuletas();
	
	Ruleta findRuletadById(String id);
	
	String updateRuleta (Ruleta ruleta);
	
	String deleteRuleta(String id);
	
	Boolean ruletaExists(String id);

}
