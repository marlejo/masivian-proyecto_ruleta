package com.masivian.proyecto.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.masivian.proyecto.model.Apuesta;
import com.masivian.proyecto.model.Ruleta;
import com.masivian.proyecto.model.Usuario;
import com.masivian.proyecto.repositories.ApuestaRepository;
import com.masivian.proyecto.repositories.RuletaRepository;
import com.masivian.proyecto.repositories.UsuarioRepository;

@RestController
@RequestMapping("/apuesta")
public class ApuestaController {
	
	ApuestaRepository apuestaRepository;
	UsuarioRepository usuarioRepository;
	RuletaRepository ruletaRepository;
	
	public ApuestaController(ApuestaRepository apuestaRepository, UsuarioRepository usuarioRepository,
			RuletaRepository ruletaRepository) {
		super();
		this.apuestaRepository = apuestaRepository;
		this.usuarioRepository = usuarioRepository;
		this.ruletaRepository = ruletaRepository;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public String addApuesta(@RequestHeader("idUsuario") String idUsuario, @RequestHeader("idRuelta") String idRuleta,
							@RequestHeader("creditosApuesta") int creditosApuesta, @RequestHeader("numeroApuesta") String numeroApuesta) {
		if(ruletaExisteYAbierta(idRuleta) && usuarioExiste(idUsuario)) {
			if(getUsuario(idUsuario).getCreditos()>=creditosApuesta || creditosApuesta>=10000) {
				if(numeroApuestaValido(numeroApuesta)) {
					String idApuesta = apuestaRepository.findAllApuestas().size()+1+"";
					apuestaRepository.saveApuesta(new Apuesta(idApuesta, getRuleta(idRuleta), getUsuario(idUsuario), creditosApuesta, numeroApuesta, true));
					return "El id de la apuesta creada es : "+idApuesta+" por un total de: "+creditosApuesta+" creditos, al numero: "+numeroApuesta;
				}else {
					return "El numero de la apuesta debe ser NEGRO o ROJO o estar entre 0 y 36";
				}
				
			}else {
				return "Los creditos de la apuesta son incorrectos";
			}
			
		}else {
			return "El Usuario o la Ruleta no existen o la ruleta esta cerrada";
		}
	}
	
	@GetMapping("/all")
	public Map<String, Apuesta> getAllUsuarios(){
		return apuestaRepository.findAllApuestas();
	}
	
	public Boolean ruletaExisteYAbierta(String idRuleta) {
		return ruletaRepository.ruletaExists(idRuleta)&&getRuleta(idRuleta).getEstado();
	}
	
	public Boolean usuarioExiste(String idUsuario) {
		return usuarioRepository.usuarioExist(idUsuario);
	}
	
	public Usuario getUsuario(String idUsuario) {
		return usuarioRepository.findUsuarioById(idUsuario);
	}
	
	public Ruleta getRuleta(String idRuleta) {
		return ruletaRepository.findRuletadById(idRuleta);
	}
	
	public Boolean numeroApuestaValido(String numeroApuesta) {
		try {
			if(numeroApuesta.toUpperCase().equals("NEGRO") || numeroApuesta.toUpperCase().equals("ROJO")) {
				return true;
			}
			else if(Integer.parseInt(numeroApuesta) >= 0 && Integer.parseInt(numeroApuesta) <= 36) {
				return true;
			}else {
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}		
	}

}
