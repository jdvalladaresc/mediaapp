/**
 * 
 */
package cl.jvalladares.service;

import cl.jvalladares.dto.ConsultaListaExamenDTO;
import cl.jvalladares.model.Consulta;

/**
 * @author josevalladares
 *
 */
public interface IConsultaService extends ICRUD<Consulta, Integer>{
	
	public Consulta registrarTransaccional(ConsultaListaExamenDTO consulta) throws Exception; 

}
