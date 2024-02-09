package es.luisherrero.apirest1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.luisherrero.apirest1.model.IncidenciasSubtipo;
import es.luisherrero.apirest1.service.IncidenciaSubtipoService;

@RestController
@RequestMapping("/subtipos")
public class IncidenciasSubtipoController {

	@Autowired
	IncidenciaSubtipoService incidenciaSubtipoService;
	
	@GetMapping
	public List<IncidenciasSubtipo> getIncidenciasSubtipo(){
		return this.incidenciaSubtipoService.getIncidenciasSubtipos();
	}
}
