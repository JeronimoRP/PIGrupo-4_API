package es.luisherrero.apirest1.service;

import java.util.ArrayList;
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

	public List<Incidencia> getByTipoAndIncidenciasSubtipoAndEstado(String tipo, String subtipoNombre, String estado) {
		List<IncidenciasSubtipo> incidenciasSubtipos = new ArrayList<>();
		List<Integer> subtipoIds = new ArrayList<>();

		if (subtipoNombre != null && !subtipoNombre.isEmpty()) {
			incidenciasSubtipos = incidenciasSubtipoRepository.findBySubtipoNombre(subtipoNombre);
			subtipoIds = incidenciasSubtipos.stream().map(IncidenciasSubtipo::getId).collect(Collectors.toList());
		}

		String tipoFiltrar = (tipo != null && !tipo.isEmpty()) ? tipo : null;
		String estadoFiltrar = (estado != null && !estado.isEmpty()) ? estado : null;

		if (tipoFiltrar != null && subtipoIds.isEmpty() && estadoFiltrar == null) {

			return incidenciaRepository.findByTipo(tipoFiltrar);
		} else if (tipoFiltrar == null && !subtipoIds.isEmpty() && estadoFiltrar == null) {

			return incidenciaRepository.findByIncidenciasSubtipo_IdIn(subtipoIds);
		} else if (tipoFiltrar == null && subtipoIds.isEmpty() && estadoFiltrar != null) {

			return incidenciaRepository.findByEstado(estadoFiltrar);
		} else if (tipoFiltrar != null && !subtipoIds.isEmpty() && estadoFiltrar == null) {

			return incidenciaRepository.findByTipoAndIncidenciasSubtipo_IdIn(tipoFiltrar, subtipoIds);
		} else if (tipoFiltrar != null && subtipoIds.isEmpty() && estadoFiltrar != null) {

			return incidenciaRepository.findByTipoAndEstado(tipoFiltrar, estadoFiltrar);
		} else if (tipoFiltrar == null && !subtipoIds.isEmpty() && estadoFiltrar != null) {

			return incidenciaRepository.findByIncidenciasSubtipo_IdInAndEstado(subtipoIds, estadoFiltrar);
		} else {

			return incidenciaRepository.findByTipoAndIncidenciasSubtipo_IdInAndEstado(tipoFiltrar, subtipoIds,
					estadoFiltrar);
		}
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
