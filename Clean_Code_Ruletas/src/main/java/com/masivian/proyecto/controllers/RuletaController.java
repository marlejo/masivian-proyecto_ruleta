package com.masivian.proyecto.controllers;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
@RequestMapping("/ruleta")
public class RuletaController {
	
	RuletaRepository ruletaRepository;
	ApuestaRepository apuestaRepository;
	UsuarioRepository usuarioRepository;

	public RuletaController(RuletaRepository ruletaRepository, ApuestaRepository apuestaRepository, UsuarioRepository usuarioRepository) {
		super();
		this.ruletaRepository = ruletaRepository;
		this.apuestaRepository = apuestaRepository;
		this.usuarioRepository = usuarioRepository;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	public String addRuleta() {
		String id=ruletaRepository.findAllRuletas().size()+1+"";
		ruletaRepository.saveRuleta(new Ruleta(id, false));
		return "El id de la ruleta creada es: "+id;
	}
	
	@GetMapping("/all")
	public Map<String, Ruleta> getAllRuletas(){
		return ruletaRepository.findAllRuletas();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/abrir_ruleta")
	public String abrirRuleta(@RequestHeader("id") String id) {
		if(ruletaExiste(id)) {
			Ruleta ruleta = ruletaRepository.findRuletadById(id);
			ruleta.setEstado(true);
			ruletaRepository.updateRuleta(ruleta);
			return "La ruleta con id: " +id+" esta abierta y ya puede realizar apuestas";
		}else {
			return "La ruleta con id: " +id+" no existe";
		}		
	}
	
	public Boolean ruletaExiste(String id) {
		return ruletaRepository.ruletaExists(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/cerrar_ruleta")
	public ArrayList<String> cerrarRuleta(@RequestHeader("id") String id) {
		ArrayList<String> respuesta = new ArrayList<>();
		if(ruletaExiste(id) && ruletaRepository.findRuletadById(id).getEstado()) {
			Ruleta ruleta = ruletaRepository.findRuletadById(id);
			ruleta.setEstado(false);
			ruletaRepository.updateRuleta(ruleta);
			respuesta = saveResultadosApuestas(id);
			respuesta.add("La ruleta con id: " +id+" esta Cerrada y no puede realizar apuestas");
			return respuesta;
		}else {
			respuesta.add("La ruleta con id: " +id+" no existe o esta cerrada");
			return respuesta;
		}	
	}
	
	public ArrayList<String> saveResultadosApuestas(String idRuleta) {
		ArrayList<String> respuesta = new ArrayList<>();
		int resultado = resultadoRuleta();
		ArrayList<Apuesta> apuestas = apuestaRepository.findAllApuestasByRuletaId(idRuleta);
		respuesta.add("NOTA: se esta jugando con una ruleta tipo europea");
		for (int i = 0; i < apuestas.size(); i++) {
			apuestas.get(i).setResultadoApuesta(resultado);
			apuestas.get(i).setEstadoApuesta(false);
			apuestaRepository.updateApuesta(apuestas.get(i));
			respuesta.add(	"La Apuesta con id: "+apuestas.get(i).getId()+" es del usuario: "+apuestas.get(i).getUsuario().getNombre()+
							" en la ruleta con id: "+apuestas.get(i).getRuleta().getId()+", donde aposto: "+apuestas.get(i).getCreditosApuesta()+
							", al numero: "+apuestas.get(i).getNumeroApuesta()+", y el resultado de la ruleta fue: "+apuestas.get(i).getResultadoApuesta()+
							", el estado de la apuesta es: "+apuestas.get(i).getEstadoApuesta());
            repartirCreditos(apuestas.get(i));
		}
		return respuesta;
	}
	
	public int resultadoRuleta() {
		return (int) (Math.random() * 36 );
	}
	
	public void repartirCreditos(Apuesta apuesta) {
		if(ganoOPerdio(apuesta)==2) {
			int ganancia = apuesta.getCreditosApuesta()*2;
			Usuario usuario = usuarioRepository.findUsuarioById(apuesta.getUsuario().getId());
			usuario.setCreditos((usuario.getCreditos()-apuesta.getCreditosApuesta())+ganancia);
			usuarioRepository.updateUsuario(usuario);
		}else if(ganoOPerdio(apuesta)==20) {
			int ganancia = apuesta.getCreditosApuesta()*20;
			Usuario usuario = usuarioRepository.findUsuarioById(apuesta.getUsuario().getId());
			usuario.setCreditos((usuario.getCreditos()-apuesta.getCreditosApuesta())+ganancia);
			usuarioRepository.updateUsuario(usuario);
		}else if(ganoOPerdio(apuesta)==-1) {
			Usuario usuario = usuarioRepository.findUsuarioById(apuesta.getUsuario().getId());
			usuario.setCreditos(usuario.getCreditos()-apuesta.getCreditosApuesta());
			usuarioRepository.updateUsuario(usuario);
		}
	}
	
	public int ganoOPerdio(Apuesta apuesta) {
		String color=colorResultadoApuesta(apuesta.getResultadoApuesta());
		if(isNumeric(apuesta.getNumeroApuesta())) {
			if(0==apuesta.getResultadoApuesta()) {
				return -1;
			}
			else if(apuesta.getResultadoApuesta() == Integer.parseInt(apuesta.getNumeroApuesta())) {
				return 20;
			}else {
				return -1;
			}
		}else if (color.equals(apuesta.getNumeroApuesta().toUpperCase())) {
			return 2;
		}else {
			return -1;
		}
	} 
	
	public String colorResultadoApuesta(int resultado) {
		if(		resultado==2 || resultado==4 || resultado==6 || resultado==8 || resultado==10 || resultado==11 ||
				resultado==13 || resultado==15 || resultado==17 || resultado==20 || resultado==22 || resultado==24 ||
				resultado==26 || resultado==28 || resultado==29 || resultado==31 || resultado==33 || resultado==35 ) {
			return "NEGRO";
		}
		else{
			return "ROJO";
		}
	}
	
	public Boolean isNumeric(String inputData) {
	      Scanner sc = new Scanner(inputData);
	      return sc.hasNextInt();
	}
}
