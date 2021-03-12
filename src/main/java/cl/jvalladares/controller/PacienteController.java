/**
 * 
 */
package cl.jvalladares.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cl.jvalladares.exception.ModeloNotFoundException;
import cl.jvalladares.model.Paciente;
import cl.jvalladares.service.IPacienteService;

/**
 * @author jvalladares
 *
 */
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired
	private IPacienteService service;

	@GetMapping
	public ResponseEntity<List<Paciente>> listar() throws Exception {
		List<Paciente> pacientes = service.listar();
		return new ResponseEntity<List<Paciente>>(pacientes, HttpStatus.OK);
	}
	
	/*
	@PostMapping
	public ResponseEntity<Paciente> registrar(@Valid @RequestBody Paciente paciente) {
		Paciente p = service.registar(paciente);
		return new ResponseEntity<Paciente>(p, HttpStatus.CREATED);
	}*/
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody Paciente paciente) throws Exception {
		Paciente p = service.registar(paciente);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getIdPaciente()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping
	public ResponseEntity<Paciente> modificar(@Valid @RequestBody Paciente paciente) throws Exception {
		Paciente p = service.modificar(paciente);
		return new ResponseEntity<Paciente>(p, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Paciente> busqueda(@PathVariable("id") Integer id) throws Exception {
		Paciente paciente = service.buscarPorId(id);
		if (paciente == null) {
			throw new ModeloNotFoundException("ID no encontrado " + id);
		}
		return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<Paciente> busquedaHateoas(@PathVariable("id") Integer id) throws Exception {
		Paciente paciente = service.buscarPorId(id);
		if (paciente == null) {
			throw new ModeloNotFoundException("ID no encontrado " + id);
		}
		
		EntityModel<Paciente> recurso = EntityModel.of(paciente);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).busqueda(id));
		
		recurso.add(linkTo.withRel("paciente-recurso"));
		
		return recurso;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Paciente paciente = service.buscarPorId(id);
		if (paciente.getIdPaciente() == null) {
			throw new ModeloNotFoundException("ID no encontrado " + id);
		}
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
