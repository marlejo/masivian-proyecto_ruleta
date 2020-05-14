package com.masivian.proyecto.repositories;

import java.util.Map;

import com.masivian.proyecto.model.Usuario;

public interface UsuarioRepository {
	String saveUsuario(Usuario usuario);
	
	Map<String,Usuario> findAllUsuarios();
	
	Usuario findUsuarioById(String id);
	
	String updateUsuario (Usuario usuario);
	
	String deleteUsuario(String id);
	
	Boolean usuarioExist(String id);

}
