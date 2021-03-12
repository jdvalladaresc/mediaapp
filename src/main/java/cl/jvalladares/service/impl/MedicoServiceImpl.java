/**
 * 
 */
package cl.jvalladares.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.jvalladares.model.Medico;
import cl.jvalladares.repo.IGenericRepo;
import cl.jvalladares.repo.IMedicoRepo;
import cl.jvalladares.service.IMedicoService;

/**
 * @author jvalladares
 *
 */
@Service
public class MedicoServiceImpl extends CRUDImpl<Medico, Integer> implements IMedicoService {

	@Autowired
	private IMedicoRepo repo;

	@Override
	protected IGenericRepo<Medico, Integer> getRepo() {
		return repo;
	}

	/*
	@Override
	public Medico registar(Medico medico) {
		return repo.save(medico);
	}

	@Override
	public Medico modificar(Medico medico) {
		return repo.save(medico);
	}

	@Override
	public List<Medico> listar() {
		return repo.findAll();
	}

	@Override
	public Medico buscarPaciente(Integer id) {
		Optional<Medico> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Medico();
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);

	}*/

}
