package es.luisherrero.apirest1.controller;

import java.util.List;
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

import es.luisherrero.apirest1.model.Perfile;
import es.luisherrero.apirest1.service.PerfileService;

@RestController
@RequestMapping("/perfile")
public class PerfileController {

	@Autowired
	private PerfileService perfileService;

	@GetMapping
	public List<Perfile> getPerfiles() {
		return perfileService.getPerfiles();
	}
	
	@GetMapping(path = "/{dominio}")
	public Optional<Perfile> getPersonalByDominio(@PathVariable("dominio") String dominio) {
		return perfileService.getByDominio(dominio);
	}
	
	@PostMapping
	public Perfile savePerfile(@RequestBody Perfile perfile) {
	    return perfileService.savePerfile(perfile);
	}
		
	@PutMapping(path = "/{id}")
	public Perfile updatePerfileById(@RequestBody Perfile perfile, @PathVariable int id) {
		return perfileService.updateById(perfile, id);
	}
	
	
	@DeleteMapping(path = "/{id}")
	public String deletePerfileById(@PathVariable("id") int id) {
		boolean deleted = perfileService.deletedPerfile(id);
		if (deleted) {
			return "Perfil con id " + id + " eliminado correctamente";
		}else {
			return "Error al eliminar aula";
		}
	}
}
