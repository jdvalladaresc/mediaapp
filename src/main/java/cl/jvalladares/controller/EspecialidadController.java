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
import cl.jvalladares.model.Especialidad;
import cl.jvalladares.service.IEspecialidadService;

/**
 * @author jvalladares
 *
 */
@RestController
@RequestMapping("/especialidades")
public class EspecialidadController {

	@Autowired
	private IEspecialidadService service;

	@GetMapping
	public ResponseEntity<List<Especialidad>> listar() throws Exception {
		List<Especialidad> pacientes = service.listar();
		return new ResponseEntity<List<Especialidad>>(pacientes, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody Especialidad especialidad) throws Exception {
		Especialidad p = service.registar(especialidad);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getIdEspecialidad()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping
	public ResponseEntity<Especialidad> modificar(@Valid @RequestBody Especialidad especialidad) throws Exception {
		Especialidad p = service.modificar(especialidad);
		return new ResponseEntity<Especialidad>(p, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Especialidad> busqueda(@PathVariable("id") Integer id) throws Exception {
		Especialidad especialidad = service.buscarPorId(id);
		if (especialidad == null) {
			throw new ModeloNotFoundException("ID no encontrado " + id);
		}
		return new ResponseEntity<Especialidad>(especialidad, HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<Especialidad> busquedaHateoas(@PathVariable("id") Integer id) throws Exception {
		Especialidad especialidad = service.buscarPorId(id);
		if (especialidad == null) {
			throw new ModeloNotFoundException("ID no encontrado " + id);
		}
		
		EntityModel<Especialidad> recurso = EntityModel.of(especialidad);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).busqueda(id));
		
		recurso.add(linkTo.withRel("especialidad-recurso"));
		
		return recurso;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Especialidad especialidad = service.buscarPorId(id);
		if (especialidad.getIdEspecialidad() == null) {
			throw new ModeloNotFoundException("ID no encontrado " + id);
		}
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
