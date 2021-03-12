/**
 * 
 */
package cl.jvalladares.repo;

import cl.jvalladares.model.Consulta;

/**
 * @author jvalladares
 *
 */
//@Repository -> No es necesario el exteriotipo por que ya hereda de  JpaRepository
public interface IConsultaRepo extends IGenericRepo<Consulta, Integer>{
	
}
