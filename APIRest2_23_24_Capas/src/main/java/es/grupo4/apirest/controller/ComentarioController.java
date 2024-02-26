package es.grupo4.apirest.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import es.grupo4.apirest.model.Comentario;
import es.grupo4.apirest.service.ComentarioService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/coment")
public class ComentarioController {
	@Autowired
	ComentarioService comentarioService;
	
	@GetMapping("/comentarios")
	public List<Comentario> getComentarios() {
		return this.comentarioService.getComentarios();
	}
	
	@PostMapping
	public Comentario saveComentario(@RequestBody Comentario comentario) {
		return comentarioService.saveComentario(comentario);
	}
	
	@GetMapping(path = "/{id}")
	public Optional<Comentario> getComentarioById(@PathVariable("id") int id){
		return this.comentarioService.getById(id);
	}

	@GetMapping("/comentarios-por-incidencia")
	public List<Comentario> getComentariosByIncidencia(@RequestParam("incidencia") String incidenciaStr) {
		try {
			int incidencia = Integer.parseInt(incidenciaStr);
			return this.comentarioService.getComentariosByIncidencia(incidencia);
		} catch (NumberFormatException e) {
			return Collections.emptyList();
		}
	}
	
	@PutMapping(path = "/{id}")
	public Comentario updateById(@RequestBody Comentario comentario, @PathVariable int id) {
		return comentarioService.updateById(comentario, id);
	}
	
	@DeleteMapping(path = "/{id}")
	public String deleteComentarioById(@PathVariable("id") int id) {
		boolean deleted = comentarioService.deletedComentario(id);
		if (deleted) {
			return "Comentario con id " + id + " eliminado correctamente";
		}else {
			return "Error al eliminar comentario";
		}
	}
	
}
