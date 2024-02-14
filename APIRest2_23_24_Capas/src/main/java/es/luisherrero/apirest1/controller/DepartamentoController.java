package es.luisherrero.apirest1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.luisherrero.apirest1.model.Departamento;
import es.luisherrero.apirest1.service.DepartamentoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/dep")
public class DepartamentoController {

	@Autowired
	DepartamentoService departamentoService;
	
	@GetMapping("/departamentos")
	public List<Departamento> getDepartamentos() {
		return this.departamentoService.getDepartamentos();
	}
	
	@PostMapping
	public Departamento saveDepartamento(@RequestBody Departamento departamento) {
		return departamentoService.saveDepartamento(departamento);
	}
	
	@GetMapping(path = "/{id}")
	public Optional<Departamento> getDepartamentoById(@PathVariable("id") int id){
		return departamentoService.getById(id);
	}
	
	@PutMapping(path = "/{id}")
	public Departamento updateDepartamentoById(@RequestBody Departamento departamento, @PathVariable int id) {
		return departamentoService.updateById(departamento, id);
	}
	
	@DeleteMapping(path = "/{id}")
	public String deletedDepartamentoById(@PathVariable("id") int id) {
		boolean deleted = departamentoService.deletedDepartamento(id);
		if (deleted) {
			return "Departamento con id " + id + " eliminado correctamente";
		}else {
			return "Error al elimianr departamento";
		}
	}
	
}
