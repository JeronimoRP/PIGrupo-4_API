package es.luisherrero.apirest1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.luisherrero.apirest1.model.Departamento;
import es.luisherrero.apirest1.repository.IDepartamentoRepository;

@Service
public class DepartamentoService {

	@Autowired
	IDepartamentoRepository departamentoRepository;
	
	public List<Departamento> getDepartamentos() {
		return this.departamentoRepository.findAll();
	}
	
	public Departamento saveDepartamento(Departamento departamento) {
		return departamentoRepository.save(departamento);
	}
	
	public Optional<Departamento> getById(int id) {
		return departamentoRepository.findById(id);
	}
	
	public Departamento updateById(Departamento request, int id) {
		Departamento departamento = departamentoRepository.findById(id).get();
		departamento.setActivo(request.getActivo());
		departamento.setCod(request.getCod());
		departamento.setNombre(request.getNombre());
		departamento.setJefedep_id(request.getJefedep_id());
		departamentoRepository.save(departamento);
		return departamento;
	}
	
	public Boolean deletedDepartamento(int id) {
		try {
			departamentoRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
}
