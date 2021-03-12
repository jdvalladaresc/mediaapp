/**
 * 
 */
package cl.jvalladares.service;

import java.util.List;

/**
 * @author josevalladares
 *
 */
public interface ICRUD<T,V> {
	
	T registar(T obj) throws Exception;
	T modificar(T obj) throws Exception;
	List<T> listar() throws Exception;
	T buscarPorId(V id) throws Exception;
	void eliminar(V id) throws Exception;
}
