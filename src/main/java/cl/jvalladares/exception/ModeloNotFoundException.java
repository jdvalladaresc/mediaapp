package cl.jvalladares.exception;

/**
 * @author jvalladares
 */
//@ResponseStatus(HttpStatus.NOT_FOUND)
public class ModeloNotFoundException extends RuntimeException{

    public ModeloNotFoundException(String mensaje) {
        super(mensaje);
    }
}
