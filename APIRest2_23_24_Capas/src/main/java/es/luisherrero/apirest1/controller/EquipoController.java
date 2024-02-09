package es.luisherrero.apirest1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.luisherrero.apirest1.model.Equipo;
import es.luisherrero.apirest1.service.EquipoService;

@RestController
@RequestMapping("/eq")
public class EquipoController {

	@Autowired
	private EquipoService equipoService;

	@GetMapping("/equipos")
	public List<Equipo> getEquipos() {	
		return equipoService.getEquipos();
	}

}
