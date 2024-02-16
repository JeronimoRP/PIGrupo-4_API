package es.luisherrero.apirest1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import enums.Tipo;
import es.luisherrero.apirest1.model.Incidencia;
import es.luisherrero.apirest1.model.IncidenciasSubtipo;
import es.luisherrero.apirest1.model.Personal;
import es.luisherrero.apirest1.repository.IIncidenciaRepository;
import es.luisherrero.apirest1.repository.IIncidenciasSubtipoRepository;
import es.luisherrero.apirest1.repository.IPersonalRepository;

@Service
public class IncidenciaService {

	@Autowired
	IIncidenciaRepository incidenciaRepository;
	
	@Autowired
	IPersonalRepository personalRepository;
	
	@Autowired
	IIncidenciasSubtipoRepository incidenciasSubtipoRepository;
	
	public List<Incidencia> getIncidencias() {
		return this.incidenciaRepository.findAll();
	}
	
	public Incidencia saveIncidencia(Incidencia incidencia) {
		return incidenciaRepository.save(incidencia);
	}
	
	public Optional<Incidencia> getById(int id) {
		return incidenciaRepository.findById(id);
	}
	
	 public Incidencia getByFiltro(Tipo tipo, String subTipo, String estado) {
	        IncidenciasSubtipo incidenciasSubtipo = incidenciasSubtipoRepository.findBySubtipoNombre(subTipo).stream().findFirst().orElse(null);
	        return incidenciaRepository.findByTipoAndIncidenciasSubtipoAndEstado(tipo, incidenciasSubtipo, estado);
	    }
	
	
	public Incidencia updateById(Incidencia request, int id) {
		Incidencia incidencia = incidenciaRepository.findById(id).get();
		incidencia.setDescripcion(request.getDescripcion());
		incidencia.setEstado(request.getEstado());
		incidencia.setFechaCierre(request.getFechaCierre());
		incidencia.setTipo(request.getTipo());
		incidencia.setIncidenciasSubtipo(request.getIncidenciasSubtipo());
		incidenciaRepository.save(incidencia);
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
