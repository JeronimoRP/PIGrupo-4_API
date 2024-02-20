package es.luisherrero.apirest1.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.luisherrero.apirest1.model.Incidencia;
import es.luisherrero.apirest1.model.IncidenciasSubtipo;
import es.luisherrero.apirest1.model.Personal;
import es.luisherrero.apirest1.repository.IIncidenciaRepository;
import es.luisherrero.apirest1.repository.IIncidenciasSubtipoRepository;
import es.luisherrero.apirest1.repository.IPersonalRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Service
public class IncidenciaService {

	@Autowired
	IIncidenciaRepository incidenciaRepository;

	@Autowired
	IPersonalRepository personalRepository;

	@Autowired
	IIncidenciasSubtipoRepository incidenciasSubtipoRepository;
	
	@PersistenceContext
    private EntityManager entityManager;

	public List<Incidencia> getIncidencias() {
		return this.incidenciaRepository.findAll();
	}

	 public List<Incidencia> getIncidenciasByCreadorId(int creadorId) {
	        return incidenciaRepository.findByPersonal1Id(creadorId);
	    }
	
	public Incidencia saveIncidencia(Incidencia incidencia) {
		return incidenciaRepository.save(incidencia);
	}

	public Optional<Incidencia> getById(int id) {
		return incidenciaRepository.findById(id);
	}
	
	public List<Incidencia> getByTipoAndIncidenciasSubtipoAndEstado(String tipo, String subtipoNombre, String estado,
			LocalDateTime date) {
		TypedQuery<Integer> subtipoQuery = entityManager.createQuery("SELECT is.id FROM IncidenciasSubtipo is WHERE is.subtipoNombre = :subtipoNombre", Integer.class);
		subtipoQuery.setParameter("subtipoNombre", subtipoNombre);
		List<Integer> subtipoIds = subtipoQuery.getResultList();
        
		StringBuilder jpqlBuilder = new StringBuilder("SELECT i FROM Incidencia i WHERE 1 = 1");
		if (tipo != null && !tipo.isEmpty()) {
            jpqlBuilder.append(" AND i.tipo = :tipo");
        }

        if (!subtipoIds.isEmpty()) {
            jpqlBuilder.append(" AND i.incidenciasSubtipo.id IN :subtipoIds");
        }

        if (estado != null && !estado.isEmpty()) {
            jpqlBuilder.append(" AND i.estado = :estado");
        }

        if (date != null) {
            jpqlBuilder.append(" AND i.fechaCreacion = :fechaCreacion");
        }

        TypedQuery<Incidencia> query = entityManager.createQuery(jpqlBuilder.toString(), Incidencia.class);

        if (tipo != null && !tipo.isEmpty()) {
            query.setParameter("tipo", tipo);
        }

        if (!subtipoIds.isEmpty()) {
            query.setParameter("subtipoIds", subtipoIds);
        }

        if (estado != null && !estado.isEmpty()) {
            query.setParameter("estado", estado);
        }

        if (date != null) {
            query.setParameter("fechaCreacion", date);
        }
        return query.getResultList();
	}

	public List<Incidencia> getByTipo(String tipo) {
		return incidenciaRepository.findByTipo(tipo);
	}

	public List<Incidencia> getBySubtipo(int subtipoId) {
		return incidenciaRepository.findByIncidenciasSubtipo_Id(subtipoId);
	}

	public List<Incidencia> getBySubtipoNombre(String subtipoNombre) {
		List<IncidenciasSubtipo> incidenciasSubtipos = incidenciasSubtipoRepository.findBySubtipoNombre(subtipoNombre);

		if (!incidenciasSubtipos.isEmpty()) {
			List<Integer> subtipoIds = incidenciasSubtipos.stream().map(IncidenciasSubtipo::getId)
					.collect(Collectors.toList());

			return incidenciaRepository.findByIncidenciasSubtipo_IdIn(subtipoIds);
		} else {
			return Collections.emptyList();
		}
	}

	public List<Incidencia> getByEstado(String estado) {
		return incidenciaRepository.findByEstado(estado);
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
