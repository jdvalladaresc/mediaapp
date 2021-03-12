/**
 * 
 */
package cl.jvalladares.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.jvalladares.model.Paciente;
import cl.jvalladares.repo.IGenericRepo;
import cl.jvalladares.repo.IPacienteRepo;
import cl.jvalladares.service.IPacienteService;

/**
 * @author jvalladares
 *
 */
@Service
public class PacienteServiceImpl extends CRUDImpl<Paciente, Integer> implements IPacienteService {

	@Autowired
	private IPacienteRepo repo;

	@Override
	protected IGenericRepo<Paciente, Integer> getRepo() {
		return repo;
	}
	
	/*
	@Override
	public Paciente registar(Paciente paciente) {
		return repo.save(paciente);
	}

	@Override
	public Paciente modificar(Paciente paciente) {
		return repo.save(paciente);
	}

	@Override
	public List<Paciente> listar() {
		return repo.findAll();
	}

	@Override
	public Paciente buscarPaciente(Integer id) {
		Optional<Paciente> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Paciente() ;
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);

	}*/

}
