package es.grupo4.apirest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.grupo4.apirest.model.Perfile;
import es.grupo4.apirest.service.PerfileService;

@RestController
@RequestMapping("/perfile")
public class PerfileController {

	@Autowired
	private PerfileService perfileService;

	@GetMapping(path = "/{dominio}")
	public Optional<Perfile> getPersonalByDominio(@PathVariable("dominio") String dominio) {
		return perfileService.getByDominio(dominio);
	}
}
