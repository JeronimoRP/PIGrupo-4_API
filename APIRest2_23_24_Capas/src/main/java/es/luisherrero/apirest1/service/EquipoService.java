package es.luisherrero.apirest1.service;

import java.util.List;
import java.util.Optional;

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
	
	public Optional<Equipo> getById(int id) {
		return equipoRepository.findById(id);
	}
	
	public Equipo updateById(Equipo request, int id) {
		Equipo equipo = equipoRepository.findById(id).get();
		equipo.setBaja(request.getBaja());
		equipo.setDescripcion(request.getDescripcion());
		equipo.setEtiqueta(request.getEtiqueta());
		equipo.setFechaAdquisicion(request.getFechaAdquisicion());
		equipo.setMarca(request.getMarca());
		equipo.setModelo(request.getModelo());
		equipo.setPuesto(request.getPuesto());
		equipo.setTipoEquipo(request.getTipoEquipo());
		equipo.setAula(request.getAula());
		equipoRepository.save(equipo);
		return equipo;
	}
	
	public Boolean deletedEquipo(int id) {
		try {
			equipoRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
