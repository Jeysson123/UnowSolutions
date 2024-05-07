package com.unowsolutions.common.exceptions;
import com.unowsolutions.common.dtos.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * Esta clase es utilizada para controlar las excepciones desde un solo punto del API.
 * @author Robert Jeysson Nin Urena
 */
@ControllerAdvice
public class ExceptionManager {
    /**
     * Mapea la exception.
     *
     * @param ex error ocurrido durante el uso del servicio
     * @return devuelve un msg de error personalizado.
     * @author Robert Jeysson Nin Urena
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

