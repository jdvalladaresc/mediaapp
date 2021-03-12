/**
 * 
 */
package cl.jvalladares.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.jvalladares.dto.ConsultaListaExamenDTO;
import cl.jvalladares.model.Consulta;
import cl.jvalladares.repo.IConsultaExamenRepo;
import cl.jvalladares.repo.IConsultaRepo;
import cl.jvalladares.repo.IGenericRepo;
import cl.jvalladares.service.IConsultaService;

/**
 * @author jvalladares
 *
 */
@Service
public class ConsultaServiceImpl extends CRUDImpl<Consulta, Integer> implements IConsultaService {

	@Autowired
	private IConsultaRepo repo;
	
	@Autowired
	private IConsultaExamenRepo ceRepo;

	@Override
	protected IGenericRepo<Consulta, Integer> getRepo() {
		return repo;
	}

	@Transactional
	@Override
	public Consulta registrarTransaccional(ConsultaListaExamenDTO dto) throws Exception {
		dto.getConsulta().getDetalleConsultas().forEach(det -> det.setConsulta(dto.getConsulta()));
		repo.save(dto.getConsulta());
		dto.getListaExamen().forEach(exa -> ceRepo.registrar(dto.getConsulta().getIdConsulta(), exa.getIdExamen()));
		
		return dto.getConsulta();
		
	}

}
