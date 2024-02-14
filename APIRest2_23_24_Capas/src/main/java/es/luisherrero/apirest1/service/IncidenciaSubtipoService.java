package es.luisherrero.apirest1.service;

import java.util.List;
import java.util.Optional;

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
	
	public IncidenciasSubtipo saveIncidenciasSubtipo(IncidenciasSubtipo incidenciasSubtipo) {
		return iIncidenciasSubtipoRepository.save(incidenciasSubtipo);
	}
	
	public Optional<IncidenciasSubtipo> getById(int id) {
		return iIncidenciasSubtipoRepository.findById(id);
	}
	
	public IncidenciasSubtipo updateById(IncidenciasSubtipo request, int id) {
		IncidenciasSubtipo incidenciasSubtipo = iIncidenciasSubtipoRepository.findById(id).get();
		incidenciasSubtipo.setSubSubtipo(request.getSubSubtipo());
		incidenciasSubtipo.setSubtipoNombre(request.getSubtipoNombre());
		incidenciasSubtipo.setTipo(request.getTipo());
		iIncidenciasSubtipoRepository.save(incidenciasSubtipo);
		return incidenciasSubtipo;
	}
	
	public Boolean deleteIncidenciaSubtipo(int id) {
		try {
			iIncidenciasSubtipoRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
}
