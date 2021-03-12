/**
 * 
 */
package cl.jvalladares.repo;

import cl.jvalladares.model.Paciente;

/**
 * @author jvalladares
 *
 */
//@Repository -> No es necesario el exteriotipo por que ya hereda de  JpaRepository
public interface IPacienteRepo extends IGenericRepo<Paciente, Integer>{

	
	
}
