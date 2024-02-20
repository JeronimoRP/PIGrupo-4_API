package es.luisherrero.apirest1.controller;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.luisherrero.apirest1.model.Incidencia;
import es.luisherrero.apirest1.service.IncidenciaService;

@RestController
@RequestMapping("/ap")
public class IncidenciaController {

	@Autowired

	private IncidenciaService incidenciaService;

	@GetMapping("/incidencias")
	public List<Incidencia> getIncidencias() {
		return incidenciaService.getIncidencias();
	}

	@GetMapping("/filtro")
	public List<Incidencia> getIncidenciasByFiltro(
	        @RequestParam(name = "tipo", required = false) String tipo,
	        @RequestParam(name = "subtipoNombre", required = false) String subtipoNombre,
	        @RequestParam(name = "estado", required = false) String estado,
	        @RequestParam(name = "fechaCreacion", required = false)
	        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime fechaCreacion) {
	    return incidenciaService.getByTipoAndIncidenciasSubtipoAndEstado(tipo, subtipoNombre, estado, fechaCreacion);
	}
	
	@GetMapping("/incidencias/tipo")
	public List<Incidencia> getIncidenciasByTipo(@RequestParam(name = "tipo") String tipo) {
	    try {
	        return incidenciaService.getByTipo(tipo);
	    } catch (IllegalArgumentException e) {
	        return Collections.emptyList();
	    }
	}
	
	@GetMapping("/creador/{creadorId}")
    public List<Incidencia> getIncidenciasByCreador(@PathVariable int creadorId) {
        return incidenciaService.getIncidenciasByCreadorId(creadorId);
    }
	
	@GetMapping("/subtipo/id/{subtipoId}")
    public List<Incidencia> getIncidenciasBySubtipo(@PathVariable int subtipoId) {
        return incidenciaService.getBySubtipo(subtipoId);
    }
	
	@GetMapping("/subtipo/nombre/{subtipoNombre}")
	public List<Incidencia> getIncidenciasBySubtipoNombre(@PathVariable String subtipoNombre) {
        return incidenciaService.getBySubtipoNombre(subtipoNombre);
    }

	@GetMapping("/incidencias/estado")
	public List<Incidencia> getIncidenciasByEstado(@RequestParam(name = "estado") String estado) {
		return incidenciaService.getByEstado(estado);
	}
	
	@PostMapping
	public Incidencia saveIncidencia(@RequestBody Incidencia incidencia) {
		return incidenciaService.saveIncidencia(incidencia);
	}

	@PutMapping(path = "/{id}")
	public Incidencia updateIncidenciaById(@RequestBody Incidencia incidencia, @PathVariable int id) {
		return incidenciaService.updateById(incidencia, id);
	}

	@PostMapping("/asignarincidencia")
	public String asignarIncidencia(@RequestParam int idUsuario, @RequestParam int idIncidencia) {

		incidenciaService.asignarIncidencia(idUsuario, idIncidencia);

		return "Incidencia asignada correctamente al usuario con ID: " + idUsuario;
	}

	@DeleteMapping(path = "/{id}")
	public String deleteIncidenciaById(@PathVariable("id") int id) {
		boolean deleted = incidenciaService.deletedIncidencia(id);
		if (deleted) {
			return "Incidencia con id " + id + "eliminado correctamente";
		} else {
			return "Error al eliminar aula";
		}
	}
}
