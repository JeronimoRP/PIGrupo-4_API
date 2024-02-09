package es.luisherrero.apirest1.service;

import java.util.List;

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
}
