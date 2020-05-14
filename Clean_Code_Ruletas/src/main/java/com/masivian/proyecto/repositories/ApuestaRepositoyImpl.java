package com.masivian.proyecto.repositories;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.masivian.proyecto.model.Apuesta;

@Repository
public class ApuestaRepositoyImpl implements ApuestaRepository {
	
	private RedisTemplate<String, Apuesta> redisTemplate;
	private HashOperations hashOperations;
	
	public ApuestaRepositoyImpl(RedisTemplate<String, Apuesta> redisTemplate) {
		super();
		this.redisTemplate = redisTemplate;
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public String saveApuesta(Apuesta apuesta) {
		hashOperations.put("Apuesta", apuesta.getId(), apuesta);
		return apuesta.getId();
	}

	@Override
	public Map<String, Apuesta> findAllApuestas() {
		return hashOperations.entries("Apuesta");
	}

	@Override
	public Apuesta findApuestaById(String id) {
		return (Apuesta)hashOperations.get("Apuesta", id);
	}

	@Override
	public String updateApuesta(Apuesta apuesta) {
		saveApuesta(apuesta);
		return "Apuesta Actualizada con exito";
	}

	@Override
	public String deleteApuesta(String id) {
		hashOperations.delete("Apuesta", id);
		return "Apuesta borrada con exito";
	}

	@Override
	public Boolean apuestaExist(String id) {
		return hashOperations.hasKey("Apuesta", id);
	}
	
	@Override
	public ArrayList<Apuesta> findAllApuestasByRuletaId(String idRuleta){
		ArrayList<Apuesta> apuestasPorRuleta = new ArrayList<>();
		Map <String, Apuesta> apuestas = findAllApuestas();
		Iterator<Map.Entry<String, Apuesta>> itr = apuestas.entrySet().iterator(); 
		while(itr.hasNext()){ 
            Map.Entry<String, Apuesta> entry = itr.next(); 
            if(entry.getValue().getRuleta().getId().equals(idRuleta)) {
            	if(entry.getValue().getEstadoApuesta()) {
            		apuestasPorRuleta.add((Apuesta) entry.getValue());
            	}            	
            } 
        }
		return apuestasPorRuleta;
	}

}
