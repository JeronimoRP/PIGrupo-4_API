package es.luisherrero.apirest1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.luisherrero.apirest1.model.IncidenciasSubtipo;
import es.luisherrero.apirest1.repository.IIncidenciasSubtipoRepository;


@Service
public class IncidenciaSubtipoService {

	@Autowired
	IIncidenciasSubtipoRepository iIncidenciasSubtipoRepository;
	
	public List<IncidenciasSubtipo> getIncidenciasSubtipos() {
		return this.iIncidenciasSubtipoRepository.findAll();
	}
	
}
