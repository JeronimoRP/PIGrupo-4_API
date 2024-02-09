package es.grupo4.apirest.service;

import java.util.List;
import java.util.Optional;

import es.grupo4.apirest.repository.IPersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.grupo4.apirest.model.Perfile;
import es.grupo4.apirest.model.Personal;

@Service
public class PersonalService {

	@Autowired
    IPersonalRepository personalRepository;
	
	public List<Personal> getPersonals() {
		return this.personalRepository.findAll();
	}
	
	public Personal savePersonal(Personal personal) {
		return personalRepository.save(personal);
	}

	public Optional<Personal> getById(int id) {
		return personalRepository.findById(id);
	}
	
	
	public Boolean deletedPersonal(int id) {
		try {
			personalRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	 public Perfile obtenerPerfilUsuario(Personal personal) {
	        return personal != null ? personal.getPerfile() : null;
	 }
	
}
