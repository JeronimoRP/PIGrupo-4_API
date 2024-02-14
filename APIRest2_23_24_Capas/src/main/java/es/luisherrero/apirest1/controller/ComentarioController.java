package es.luisherrero.apirest1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.luisherrero.apirest1.model.Comentario;
import es.luisherrero.apirest1.service.ComentarioService;
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
