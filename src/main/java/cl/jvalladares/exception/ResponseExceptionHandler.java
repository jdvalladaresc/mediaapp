package cl.jvalladares.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;


/**
 * @author jvalladares
 */
@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private String mensaje;

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse>manejarTodasExcepciones(ModeloNotFoundException ex, WebRequest request){
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ExceptionResponse>(er, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ModeloNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> manejarModeloException(ModeloNotFoundException ex, WebRequest request){
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ExceptionResponse>(er, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        mensaje = "";

        ex.getBindingResult().getAllErrors().forEach(e->{
            mensaje += e.getDefaultMessage() + "\n";
        });
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), mensaje, request.getDescription(false));
        return new ResponseEntity<Object>(er, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(DataIntegrityViolationException ex, WebRequest request) {
        if (ex.getCause() instanceof ConstraintViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) ex.getCause();

            if(cve.getCause() instanceof SQLIntegrityConstraintViolationException){
                SQLIntegrityConstraintViolationException sicve = (SQLIntegrityConstraintViolationException) cve.getCause();
                ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), sicve.getMessage(), request.getDescription(false));
                return new ResponseEntity<Object>(er, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<Object>(cve, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
