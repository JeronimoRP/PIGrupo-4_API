package services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.Aula;
import repositories.IAulaRepository;

@Service
public class AulaService {
	
	@Autowired
	IAulaRepository aulaRepository;
	
	public ArrayList<Aula> getAulas() {
		return (ArrayList<Aula>) aulaRepository.findAll();
	}
	
	public Aula saveAula(Aula aula) {
		return aulaRepository.save(aula);
	}

	public Optional<Aula> getById(int id) {
		return aulaRepository.findById(id);
	}
	
	public Aula updateById(Aula request, int id) {
		Aula aula = aulaRepository.findById(id).get();
		aula.setCodigo(request.getCodigo());
		aula.setDescripcion(request.getDescripcion());
		aula.setEquipos(request.getEquipos());
		aula.setPlanta(request.getPlanta());
		return aula;
	}
	
	public Boolean deletedAula(int id) {
		try {
			aulaRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
