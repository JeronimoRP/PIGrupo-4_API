package es.grupo4.apirest.controller;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import es.grupo4.apirest.Dto.IncidenciaDto;
import es.grupo4.apirest.Dto.IncidenciaFilterDto;
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

import es.grupo4.apirest.model.Incidencia;
import es.grupo4.apirest.service.IncidenciaService;

@RestController
@RequestMapping("/incidencias")
public class IncidenciaController {

	@Autowired
	private IncidenciaService incidenciaService;

	@GetMapping("/incidencias")
	public List<IncidenciaDto> getIncidencias() {
		return incidenciaService.getIncidencias();
	}

	@GetMapping("/filtro")
	public List<IncidenciaDto> getIncidenciasByFiltro(
			@RequestBody IncidenciaFilterDto filtro,
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime fechaCreacion) {
	    return incidenciaService.getByTipoAndIncidenciasSubtipoAndEstado(filtro);
	}
	@PostMapping("/incidencias/{creadorId}")
	public List<IncidenciaDto> getIncidenciasByCreador(@PathVariable int creadorId) {
		return incidenciaService.getIncidenciasByCreadorId(creadorId);
	}
	
	@PostMapping("/save")
	public void saveIncidencia(@RequestBody IncidenciaDto incidenciaDto) {
		incidenciaService.saveIncidencia(incidenciaDto);
	}

	@PutMapping(path = "update/")
	public void updateIncidenciaById(@RequestBody IncidenciaDto incidenciaDto) {
		incidenciaService.updateById(incidenciaDto);
	}

	@PostMapping("/asignarincidencia")
	public String asignarIncidencia(@RequestParam int idUsuario, @RequestParam int idIncidencia) {

		incidenciaService.asignarIncidencia(idUsuario, idIncidencia);

		return "Incidencia asignada correctamente al usuario con ID: " + idUsuario;
	}

	@DeleteMapping(path = "delete/{id}")
	public String deleteIncidenciaById(@PathVariable("id") int id) {
		boolean deleted = incidenciaService.deletedIncidencia(id);
		if (deleted) {
			return "Incidencia con id " + id + " eliminado correctamente";
		} else {
			return "Error al eliminar aula";
		}
	}

	@GetMapping("/abiertas")
	public List<IncidenciaDto> getIncidenciasAbiertas(){
		return incidenciaService.getIncidenciasAbiertas();
	}
}
