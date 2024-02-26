package es.grupo4.apirest.service;

import java.util.List;
import java.util.Optional;

import es.grupo4.apirest.model.Comentario;
import es.grupo4.apirest.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

	@Autowired
    ComentarioRepository comentarioRepository;
	
	public List<Comentario> getComentarios() {
		return this.comentarioRepository.findAll();
	}
	public List<Comentario> getComentariosByIncidencia(int incidencia){return this.comentarioRepository.findAllByIncidencia_Num(incidencia);}
	public Comentario saveComentario(Comentario comentario) {
		return this.comentarioRepository.save(comentario);
	}
	
	public Optional<Comentario> getById(int id) {
		return this.comentarioRepository.findById(id);
	}
	
	public Comentario updateById(Comentario request, int id) {
		Comentario comentario = comentarioRepository.findById(id).get();
		comentario.setAdjuntoUrl(request.getAdjuntoUrl());
		comentario.setFechahora(request.getFechahora());
		comentario.setTexto(request.getTexto());
		comentario.setIncidencia(request.getIncidencia());
		comentario.setPersonal(request.getPersonal());
		comentarioRepository.save(comentario);
		return comentario;
	}
	
	public Boolean deletedComentario(int id) {
		try {
			this.comentarioRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
}
