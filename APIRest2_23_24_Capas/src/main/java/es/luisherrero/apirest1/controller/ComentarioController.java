package es.luisherrero.apirest1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.luisherrero.apirest1.model.Comentario;
import es.luisherrero.apirest1.service.ComentarioService;

@RestController
@RequestMapping("/coment")
public class ComentarioController {

	@Autowired
	ComentarioService comentarioService;
	
	@GetMapping("/comentarios")
	public List<Comentario> getComentarios() {
		return this.comentarioService.getComentarios();
	}
}
