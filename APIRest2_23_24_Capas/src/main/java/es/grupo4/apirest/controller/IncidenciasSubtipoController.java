package es.grupo4.apirest.controller;

import java.util.List;

import es.grupo4.apirest.service.IncidenciaSubtipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.grupo4.apirest.model.IncidenciasSubtipo;

@RestController
@RequestMapping("/subtipos")
public class IncidenciasSubtipoController {

	@Autowired
	IncidenciaSubtipoService incidenciaSubtipoService;
	
	@GetMapping
	public List<IncidenciasSubtipo> getIncidenciasSubtipo(){
		return this.incidenciaSubtipoService.getIncidenciasSubtipos();
	}
	
	@PostMapping
	public IncidenciasSubtipo saveIncidenciaSubtipo(@RequestBody IncidenciasSubtipo incidenciaSubtipo) {
	    return incidenciaSubtipoService.saveIncidenciasSubtipo(incidenciaSubtipo);
	}
	
	@PutMapping(path = "/{id}")
	public IncidenciasSubtipo updateIncidenciaSubtipoById(@RequestBody IncidenciasSubtipo incidenciaSubtipo, @PathVariable int id) {
		return incidenciaSubtipoService.updateById(incidenciaSubtipo, id);
	}
	
	@DeleteMapping(path = "/{id}")
	public String deleteIncidenciaById(@PathVariable("id") int id) {
		boolean deleted = incidenciaSubtipoService.deleteIncidenciaSubtipo(id);
		if (deleted) {
			return "Incidencia Subtipo con id " + id + "eliminado correctamente";
		}else {
			return "Error al eliminar aula";
		}
	}
}
