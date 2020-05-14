package com.masivian.proyecto.repositories;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.masivian.proyecto.model.Ruleta;

@Repository
public class RuletaRepositoyImpl implements RuletaRepository {
	
	private RedisTemplate<String, Ruleta> redisTemplate;
	private HashOperations hashOperations;
	
	public RuletaRepositoyImpl(RedisTemplate<String, Ruleta> redisTemplate) {
		super();
		this.redisTemplate = redisTemplate;
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public String saveRuleta(Ruleta ruleta) {
		hashOperations.put("Ruleta", ruleta.getId(), ruleta);
		return ruleta.getId();
	}

	@Override
	public Map<String, Ruleta> findAllRuletas() {
		return hashOperations.entries("Ruleta");
	}

	@Override
	public Ruleta findRuletadById(String id) {
		return (Ruleta)hashOperations.get("Ruleta", id);
	}

	@Override
	public String updateRuleta(Ruleta ruleta) {
		saveRuleta(ruleta);
		return "Ruleta actualizada con exito";
	}

	@Override
	public String deleteRuleta(String id) {
		hashOperations.delete("Ruleta", id);
		return "Ruleta borrada con exito";
	}
	
	@Override
	public Boolean ruletaExists(String id) {
		return hashOperations.hasKey("Ruleta", id);
	}

}
