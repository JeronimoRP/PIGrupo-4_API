package es.grupo4.apirest.service;

import java.sql.Date;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

import es.grupo4.apirest.Dto.ComentarioDto;
import es.grupo4.apirest.model.Comentario;
import es.grupo4.apirest.model.Incidencia;
import es.grupo4.apirest.model.Personal;
import es.grupo4.apirest.repository.ComentarioRepository;
import es.grupo4.apirest.repository.IncidenciaRepository;
import es.grupo4.apirest.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

	@Autowired
    ComentarioRepository comentarioRepository;
	@Autowired
	IncidenciaRepository incidenciaRepository;
	@Autowired
	PersonalRepository personalRepository;
	
	public List<Comentario> getComentarios() {
		return this.comentarioRepository.findAll();
	}
	
	public void saveComentario(ComentarioDto dto) {
		Comentario comentario=ComentarioDto.toEntity(dto);
		if(dto.getIncidencia()!=null){
			Incidencia incidencia=incidenciaRepository.findById(dto.getIncidencia()).orElse(null);
			comentario.setIncidencia(incidencia);
		}
		if(dto.getPersonal()!=null){
			Personal personal=personalRepository.findById(dto.getPersonal()).orElse(null);
			comentario.setPersonal(personal);
		}
		this.comentarioRepository.save(comentario);
	}
	
	public Optional<Comentario> getById(int id) {
		return this.comentarioRepository.findById(id);
	}
	
	public void updateById(ComentarioDto request) {
		Comentario comentario = comentarioRepository.findById(request.getId()).get();
		if(request.getAdjuntoUrl()!=null){
			comentario.setAdjuntoUrl(request.getAdjuntoUrl());
		}
		if(request.getFechahora()!=null){
			comentario.setFechahora(Date.from(request.getFechahora().toInstant(ZoneOffset.UTC)));
		}
		if(request.getTexto()!=null){
			comentario.setTexto(request.getTexto());
		}
		if(request.getIncidencia()!=null){
			Incidencia incidencia=incidenciaRepository.findById(request.getIncidencia()).orElse(null);
			comentario.setIncidencia(incidencia);
		}
		if(request.getPersonal()!=null){
			Personal personal=personalRepository.findById(request.getPersonal()).orElse(null);
			comentario.setPersonal(personal);
		}
		comentarioRepository.save(comentario);
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
