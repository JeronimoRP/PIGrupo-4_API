package es.grupo4.apirest.service;

import java.util.List;
import java.util.Optional;

import es.grupo4.apirest.repository.IIncidenciaRepository;
import es.grupo4.apirest.repository.IPersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.grupo4.apirest.model.Incidencia;
import es.grupo4.apirest.model.Personal;

@Service
public class IncidenciaService {

	@Autowired
    IIncidenciaRepository incidenciaRepository;
	
	@Autowired
    IPersonalRepository personalRepository;
	
	public List<Incidencia> getIncidencias() {
		return this.incidenciaRepository.findAll();
	}
	
	public Incidencia saveIncidencia(Incidencia incidencia) {
		return incidenciaRepository.save(incidencia);
	}
	
	public Optional<Incidencia> getById(int id) {
		return incidenciaRepository.findById(id);
	}
	
	public Incidencia updateById(Incidencia request, int id) {
		Incidencia incidencia = incidenciaRepository.findById(id).get();
		incidencia.setDescripcion(request.getDescripcion());
		incidencia.setEstado(request.getEstado());
		incidencia.setFechaCierre(request.getFechaCierre());
		incidencia.setTipo(request.getTipo());
		incidencia.setIncidenciasSubtipo(request.getIncidenciasSubtipo());
		return incidencia;
	}
	
	public void asignarIncidencia(int idPersonal, int idIncidencia) {
        Personal personal = personalRepository.findById(idPersonal).orElse(null);
        Incidencia incidencia = incidenciaRepository.findById(idIncidencia).orElse(null);

        if (personal != null && incidencia != null) {
        	incidenciaRepository.save(incidencia);
            incidencia.setPersonal1(personal);
        } else {
            throw new IllegalArgumentException("Usuario o incidencia no encontrados");
        }
    }
	
	public Boolean deletedIncidencia(int id) {
		try {
			incidenciaRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
