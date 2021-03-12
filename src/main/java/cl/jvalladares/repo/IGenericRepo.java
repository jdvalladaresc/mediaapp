/**
 * 
 */
package cl.jvalladares.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author josevalladares
 *
 */
@NoRepositoryBean
public interface IGenericRepo<T, ID> extends JpaRepository<T, ID>{

}
