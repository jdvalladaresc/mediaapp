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
import cl.jvalladares.model.Examen;
import cl.jvalladares.service.IExamenService;

/**
 * @author jvalladares
 *
 */
@RestController
@RequestMapping("/examenes")
public class ExamenController {

	@Autowired
	private IExamenService service;

	@GetMapping
	public ResponseEntity<List<Examen>> listar() throws Exception {
		List<Examen> pacientes = service.listar();
		return new ResponseEntity<List<Examen>>(pacientes, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody Examen examen) throws Exception {
		Examen p = service.registar(examen);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getIdExamen()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping
	public ResponseEntity<Examen> modificar(@Valid @RequestBody Examen examen) throws Exception {
		Examen p = service.modificar(examen);
		return new ResponseEntity<Examen>(p, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Examen> busqueda(@PathVariable("id") Integer id) throws Exception {
		Examen examen = service.buscarPorId(id);
		if (examen == null) {
			throw new ModeloNotFoundException("ID no encontrado " + id);
		}
		return new ResponseEntity<Examen>(examen, HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<Examen> busquedaHateoas(@PathVariable("id") Integer id) throws Exception {
		Examen examen = service.buscarPorId(id);
		if (examen == null) {
			throw new ModeloNotFoundException("ID no encontrado " + id);
		}
		
		EntityModel<Examen> recurso = EntityModel.of(examen);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).busqueda(id));
		
		recurso.add(linkTo.withRel("examen-recurso"));
		
		return recurso;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Examen examen = service.buscarPorId(id);
		if (examen.getIdExamen() == null) {
			throw new ModeloNotFoundException("ID no encontrado " + id);
		}
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
