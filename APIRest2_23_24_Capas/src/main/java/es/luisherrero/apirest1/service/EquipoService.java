package es.luisherrero.apirest1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.luisherrero.apirest1.model.Equipo;
import es.luisherrero.apirest1.repository.IEquipoRepository;

@Service
public class EquipoService {

	@Autowired
	IEquipoRepository equipoRepository;
	
	public List<Equipo> getEquipos() {
		return this.equipoRepository.findAll();
	}
	
	public Equipo saveEquipo(Equipo equipo) {
		return equipoRepository.save(equipo);
	}
}
