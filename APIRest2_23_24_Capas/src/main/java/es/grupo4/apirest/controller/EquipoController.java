package es.grupo4.apirest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.grupo4.apirest.model.Equipo;
import es.grupo4.apirest.service.EquipoService;

@RestController
@RequestMapping("/eq")
public class EquipoController {

	@Autowired
	private EquipoService equipoService;

	@GetMapping("/equipos")
	public List<Equipo> getEquipos() {	
		return equipoService.getEquipos();
	}
	
	@PostMapping
	public Equipo saveEquipo(@RequestBody Equipo equipo) {
	    return equipoService.saveEquipo(equipo);
	}
	
	@GetMapping(path = "/{id}")
	public Optional<Equipo> getEquipoById(@PathVariable("id") int id){
		return  equipoService.getById(id);
	}
	
	
	@PutMapping(path = "/{id}")
	public Equipo updateEquipoById(@RequestBody Equipo equipo, @PathVariable int id) {
		return equipoService.updateById(equipo, id);
	}
	
	
	@DeleteMapping(path = "/{id}")
	public String deleteEquipoById(@PathVariable("id") int id) {
		boolean deleted = equipoService.deletedEquipo(id);
		if (deleted) {
			return "Equipo con id " + id + " eliminado correctamente";
		}else {
			return "Error al eliminar aula";
		}
	}
}
