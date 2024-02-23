package es.grupo4.apirest.service;

import java.util.List;
import java.util.Optional;

import es.grupo4.apirest.model.Departamento;
import es.grupo4.apirest.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartamentoService {

	@Autowired
    DepartamentoRepository departamentoRepository;
	
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
