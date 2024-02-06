package controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.Aula;
import services.AulaService;

@RestController
@RequestMapping("/aula")
public class AulaController {
	
	@Autowired
	private AulaService aulaService;
	
	@GetMapping
	public ArrayList<Aula> getAulas(){
		return aulaService.getAulas();
	}
	
	@PostMapping
	public Aula saveAula(@RequestBody Aula aula) {
	    return aulaService.saveAula(aula);
	}
	
	@GetMapping(path = "/{id}")
	public Optional<Aula> getAulaById(@PathVariable("id") int id){
		return  aulaService.getById(id);
	}
	
	
	@PutMapping
	public Aula updateAulaById(@RequestBody Aula aula, int id) {
		return aulaService.updateById(aula, id);
	}
	
	
	@DeleteMapping(path = "/{id}")
	public String deleteAulaById(@PathVariable("id") int id) {
		boolean deleted = aulaService.deletedAula(id);
		if (deleted) {
			return "Aula con id " + id + "eliminado correctamente";
		}else {
			return "Error al eliminar aula";
		}
	}
	
}