package es.luisherrero.apirest1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.luisherrero.apirest1.model.Perfile;
import es.luisherrero.apirest1.repository.IPerfileRepository;

@Service
public class PerfileService {
	@Autowired
	IPerfileRepository perfileRepository;

	public List<Perfile> getPerfiles() {
		return this.perfileRepository.findAll();
	}
	
	public Optional<Perfile> getByDominio(String dominio) {
		return perfileRepository.getPersonalByDominio(dominio);
	}
}
