package es.luisherrero.apirest1.service;

import java.util.List;
import java.util.Optional;

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
