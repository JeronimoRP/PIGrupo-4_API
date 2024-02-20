package es.grupo4.apirest.service;

import java.util.List;
import java.util.Optional;

import es.grupo4.apirest.model.Perfile;
import es.grupo4.apirest.repository.PerfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfileService {
	@Autowired
    PerfileRepository perfileRepository;

	public List<Perfile> getPerfiles() {
		return this.perfileRepository.findAll();
	}
	
	public Perfile savePerfile(Perfile perfile) {
		return perfileRepository.save(perfile);
	}

	
	public Optional<Perfile> getByDominio(String dominio) {
		return perfileRepository.getPersonalByDominio(dominio);
	}
	
	public Perfile updateById(Perfile request, int id) {
		Perfile perfile = perfileRepository.findById(id).get();
		perfile.setPerfil(request.getPerfil());
		perfile.setPassword(request.getPassword());
		perfileRepository.save(perfile);
		return perfile;
	}
	
	public Boolean deletedPerfile(int id) {
		try {
			perfileRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
