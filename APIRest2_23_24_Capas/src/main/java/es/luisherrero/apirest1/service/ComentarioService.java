package es.luisherrero.apirest1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.luisherrero.apirest1.model.Comentario;
import es.luisherrero.apirest1.repository.IComentarioRepository;

@Service
public class ComentarioService {

	@Autowired
	IComentarioRepository comentarioRepository;
	
	public List<Comentario> getComentarios() {
		return this.comentarioRepository.findAll();
	}
}
