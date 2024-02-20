package es.grupo4.apirest.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.grupo4.apirest.model.Perfile;
import es.grupo4.apirest.model.Personal;
import es.grupo4.apirest.repository.PerfileRepository;
import es.grupo4.apirest.repository.PersonalRepository;

@Service
public class PersonalService {

	@Autowired
	PersonalRepository personalRepository;

	@Autowired
    PerfileRepository perfileRepository;

	public List<Personal> getPersonals() {
		return this.personalRepository.findAll();
	}

	public Personal savePersonal(Personal personal) {
		return personalRepository.save(personal);
	}

	public Optional<Personal> getById(int id) {
		return personalRepository.findById(id);
	}
	
	public Personal getByNombreApellidos(String nombre, String apellido1, String apellido2) {
		return personalRepository.findByNombreAndApellido1AndApellido2(nombre,apellido1,apellido2).orElse(null);
	}

	public Personal updateById(Personal request, int id) {
		Personal personal = personalRepository.findById(id).get();
		personal.setActivo(request.getActivo());
		personal.setDepartamento(request.getDepartamento());
		personalRepository.save(personal);
		return personal;
	}
	
	public Boolean deletedPersonal(int id) {
		try {
			personalRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Optional<Perfile> obtenerPerfilUsuario(Personal personal) {
	      return personal != null ? perfileRepository.findById(personal.getId()) : null;
	 }

}
