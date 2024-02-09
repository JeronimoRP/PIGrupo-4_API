package es.grupo4.apirest.service;

import java.util.Optional;

import es.grupo4.apirest.repository.IPerfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.grupo4.apirest.model.Perfile;

@Service
public class PerfileService {
	@Autowired
    IPerfileRepository perfileRepository;

	public Optional<Perfile> getByDominio(String dominio) {
		return perfileRepository.getPersonalByDominio(dominio);
	}
}
