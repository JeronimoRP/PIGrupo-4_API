package es.grupo4.apirest.controller;

import java.util.List;
import java.util.Optional;

import es.grupo4.apirest.Dto.ComentarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.grupo4.apirest.model.Comentario;
import es.grupo4.apirest.service.ComentarioService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/comment")
public class ComentarioController {
	@Autowired
	ComentarioService comentarioService;
	
	@GetMapping("/comentarios")
	public List<Comentario> getComentarios() {
		return this.comentarioService.getComentarios();
	}
	
	@PostMapping("/save")
	public void saveComentario(@RequestBody ComentarioDto dto) {
		comentarioService.saveComentario(dto);
	}
	
	@GetMapping(path = "/comentario/{id}")
	public Optional<Comentario> getComentarioById(@PathVariable("id") int id){
		return this.comentarioService.getById(id);
	}
	
	@PutMapping(path = "/update/{id}")
	public void updateById(@RequestBody ComentarioDto dto) {
		comentarioService.updateById(dto);
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public String deleteComentarioById(@PathVariable("id") int id) {
		boolean deleted = comentarioService.deletedComentario(id);
		if (deleted) {
			return "Comentario con id " + id + " eliminado correctamente";
		}else {
			return "Error al eliminar comentario";
		}
	}
	
}
