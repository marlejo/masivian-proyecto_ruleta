package com.masivian.proyecto.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.masivian.proyecto.model.Ruleta;
import com.masivian.proyecto.model.Usuario;
import com.masivian.proyecto.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	UsuarioRepository usuarioRepository;

	public UsuarioController(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public String addUsuario(@RequestHeader("nombre") String nombre, @RequestHeader("creditos") int	creditos) {
		String id = usuarioRepository.findAllUsuarios().size()+1+"";
		usuarioRepository.saveUsuario(new Usuario(id, nombre, creditos));
		return "El id del usuario creado es: "+id;
	}	
	
	@GetMapping("/all")
	public Map<String, Usuario> getAllUsuarios(){
		return usuarioRepository.findAllUsuarios();
	}

}
