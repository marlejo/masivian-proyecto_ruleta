package com.masivian.proyecto.repositories;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.masivian.proyecto.model.Usuario;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {
	
	private RedisTemplate<String, Usuario> redisTemplate;
	private HashOperations hashOperations;
	
	public UsuarioRepositoryImpl(RedisTemplate<String, Usuario> redisTemplate) {
		super();
		this.redisTemplate = redisTemplate;
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public String saveUsuario(Usuario usuario) {
		hashOperations.put("Usuario", usuario.getId(), usuario);
		return usuario.getId();
	}

	@Override
	public Map<String, Usuario> findAllUsuarios() {
		return hashOperations.entries("Usuario");
	}

	@Override
	public Usuario findUsuarioById(String id) {
		return (Usuario)hashOperations.get("Usuario", id);
	}

	@Override
	public String updateUsuario(Usuario usuario) {
		saveUsuario(usuario);
		return "Usuario actualizado con exito";
	}

	@Override
	public String deleteUsuario(String id) {
		hashOperations.delete("Usuario", id);
		return "Usuario borrado con exito";
	}

	@Override
	public Boolean usuarioExist(String id) {
		return hashOperations.hasKey("Usuario", id);
	}

}
