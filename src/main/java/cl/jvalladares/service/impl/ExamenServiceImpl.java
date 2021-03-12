/**
 * 
 */
package cl.jvalladares.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.jvalladares.model.Examen;
import cl.jvalladares.repo.IExamenRepo;
import cl.jvalladares.repo.IGenericRepo;
import cl.jvalladares.service.IExamenService;

/**
 * @author jvalladares
 *
 */
@Service
public class ExamenServiceImpl extends CRUDImpl<Examen, Integer> implements IExamenService {

	@Autowired
	private IExamenRepo repo;

	@Override
	protected IGenericRepo<Examen, Integer> getRepo() {
		return repo;
	}

}
