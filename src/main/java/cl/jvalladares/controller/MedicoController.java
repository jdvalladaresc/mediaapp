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
import cl.jvalladares.model.Medico;
import cl.jvalladares.service.IMedicoService;

/**
 * @author jvalladares
 *
 */
@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private IMedicoService service;

	@GetMapping
	public ResponseEntity<List<Medico>> listar() throws Exception {
		List<Medico> pacientes = service.listar();
		return new ResponseEntity<List<Medico>>(pacientes, HttpStatus.OK);
	}
	
	/*
	@PostMapping
	public ResponseEntity<Medico> registrar(@Valid @RequestBody Medico medico) {
		Medico p = service.registar(medico);
		return new ResponseEntity<Medico>(p, HttpStatus.CREATED);
	}*/
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody Medico medico) throws Exception {
		Medico p = service.registar(medico);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getIdMedico()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping
	public ResponseEntity<Medico> modificar(@Valid @RequestBody Medico medico) throws Exception {
		Medico p = service.modificar(medico);
		return new ResponseEntity<Medico>(p, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Medico> busqueda(@PathVariable("id") Integer id) throws Exception {
		Medico medico = service.buscarPorId(id);
		if (medico == null) {
			throw new ModeloNotFoundException("ID no encontrado " + id);
		}
		return new ResponseEntity<Medico>(medico, HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<Medico> busquedaHateoas(@PathVariable("id") Integer id) throws Exception {
		Medico medico = service.buscarPorId(id);
		if (medico == null) {
			throw new ModeloNotFoundException("ID no encontrado " + id);
		}
		
		EntityModel<Medico> recurso = EntityModel.of(medico);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).busqueda(id));
		
		recurso.add(linkTo.withRel("medico-recurso"));
		
		return recurso;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Medico medico = service.buscarPorId(id);
		if (medico.getIdMedico() == null) {
			throw new ModeloNotFoundException("ID no encontrado " + id);
		}
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
