/**
 * 
 */
package cl.jvalladares.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.jvalladares.model.Especialidad;
import cl.jvalladares.repo.IEspecialidadRepo;
import cl.jvalladares.repo.IGenericRepo;
import cl.jvalladares.service.IEspecialidadService;

/**
 * @author jvalladares
 *
 */
@Service
public class EspecialidadServiceImpl extends CRUDImpl<Especialidad, Integer> implements IEspecialidadService {

	@Autowired
	private IEspecialidadRepo repo;

	@Override
	protected IGenericRepo<Especialidad, Integer> getRepo() {
		return repo;
	}

}
