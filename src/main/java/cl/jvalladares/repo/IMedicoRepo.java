/**
 * 
 */
package cl.jvalladares.repo;

import cl.jvalladares.model.Medico;

/**
 * @author josevalladares
 *
 */
//@Repository -> No es necesario el exteriotipo por que ya hereda de  JpaRepository
public interface IMedicoRepo extends IGenericRepo<Medico, Integer>{

}
