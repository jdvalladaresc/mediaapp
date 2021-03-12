/**
 * 
 */
package cl.jvalladares.repo;

import cl.jvalladares.model.Examen;

/**
 * @author jvalladares
 *
 */
//@Repository -> No es necesario el exteriotipo por que ya hereda de  JpaRepository
public interface IExamenRepo extends IGenericRepo<Examen, Integer>{
	
}
