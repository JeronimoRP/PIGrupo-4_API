package es.luisherrero.apirest1.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.luisherrero.apirest1.model.Perfile;
import es.luisherrero.apirest1.model.Personal;
import es.luisherrero.apirest1.repository.IPerfileRepository;
import es.luisherrero.apirest1.repository.IPersonalRepository;

@Service
public class PersonalService {

	@Autowired
	IPersonalRepository personalRepository;

	@Autowired
	IPerfileRepository perfileRepository;

	public List<Personal> getPersonals() {
		return this.personalRepository.findAll();
	}

	public Personal savePersonal(Personal personal) {
		return personalRepository.save(personal);
	}

	public Optional<Personal> getById(int id) {
		return personalRepository.findById(id);
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
