/**
 * 
 */
package cl.jvalladares.service.impl;

import java.util.List;

import cl.jvalladares.repo.IGenericRepo;
import cl.jvalladares.service.ICRUD;

/**
 * @author josevalladares
 *
 */
public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID>{

	protected abstract IGenericRepo<T,ID> getRepo();
	
	@Override
	public T registar(T obj) throws Exception {
		return getRepo().save(obj);
	}
	
	@Override
	public T modificar(T obj) throws Exception {
		return getRepo().save(obj);
	}
	
	@Override
	public List<T> listar() throws Exception {
		return getRepo().findAll();
	}
	
	@Override
	public T buscarPorId(ID id) throws Exception {
		return getRepo().findById(id).orElse(null);
	}
	
	@Override
	public void eliminar(ID id) throws Exception {
		getRepo().deleteById(id);
	}

}
