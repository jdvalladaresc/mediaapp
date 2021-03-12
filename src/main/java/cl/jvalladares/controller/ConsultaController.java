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

import cl.jvalladares.dto.ConsultaListaExamenDTO;
import cl.jvalladares.exception.ModeloNotFoundException;
import cl.jvalladares.model.Consulta;
import cl.jvalladares.service.IConsultaService;

/**
 * @author jvalladares
 *
 */
@RestController
@RequestMapping("/consultas")
public class ConsultaController {

	@Autowired
	private IConsultaService service;

	@GetMapping
	public ResponseEntity<List<Consulta>> listar() throws Exception {
		List<Consulta> consultas = service.listar();
		return new ResponseEntity<List<Consulta>>(consultas, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody ConsultaListaExamenDTO dto) throws Exception {
		Consulta c = service.registrarTransaccional(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getIdConsulta()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping
	public ResponseEntity<Consulta> modificar(@Valid @RequestBody Consulta consulta) throws Exception {
		Consulta c = service.modificar(consulta);
		return new ResponseEntity<Consulta>(c, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Consulta> busqueda(@PathVariable("id") Integer id) throws Exception {
		Consulta consulta = service.buscarPorId(id);
		if (consulta == null) {
			throw new ModeloNotFoundException("ID no encontrado " + id);
		}
		return new ResponseEntity<Consulta>(consulta, HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<Consulta> busquedaHateoas(@PathVariable("id") Integer id) throws Exception {
		Consulta consulta = service.buscarPorId(id);
		if (consulta == null) {
			throw new ModeloNotFoundException("ID no encontrado " + id);
		}
		
		EntityModel<Consulta> recurso = EntityModel.of(consulta);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).busqueda(id));
		
		recurso.add(linkTo.withRel("consulta-recurso"));
		
		return recurso;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Consulta consulta = service.buscarPorId(id);
		if (consulta.getIdConsulta() == null) {
			throw new ModeloNotFoundException("ID no encontrado " + id);
		}
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
