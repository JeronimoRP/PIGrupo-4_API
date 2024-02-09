package es.luisherrero.apirest1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.luisherrero.apirest1.model.Departamento;
import es.luisherrero.apirest1.service.DepartamentoService;

@RestController
@RequestMapping("/dep")
public class DepartamentoController {

	@Autowired
	DepartamentoService departamentoService;
	
	@GetMapping("/departamentos")
	public List<Departamento> getDepartamentos() {
		return this.departamentoService.getDepartamentos();
	}
}
