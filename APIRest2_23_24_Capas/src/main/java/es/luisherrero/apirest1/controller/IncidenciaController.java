package es.luisherrero.apirest1.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import enums.Tipo;
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

	@GetMapping("/incidencias/filtro")
	public Optional<Incidencia> getAulaById(@RequestParam(name = "tipo") Tipo tipo,
			@RequestParam(name = "subTipo") String subTipo, @RequestParam(name = "estado") String estado) {
		return incidenciaService.getByFiltro(tipo, subTipo, estado);
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