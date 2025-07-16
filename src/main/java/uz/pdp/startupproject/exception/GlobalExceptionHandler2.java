package uz.pdp.startupproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.startupproject.payload.ErrorDTO;

import java.sql.Timestamp;

/**
 * Created by: Umar
 * DateTime: 2/10/2025 4:46 PM
 */
@RestControllerAdvice
public class GlobalExceptionHandler2 {

    @ExceptionHandler(RestException.class)
    public ResponseEntity<ErrorDTO> handle(RestException e) {
        ErrorDTO errorDTO = new ErrorDTO(
                new Timestamp(System.currentTimeMillis()),
                e.getMessage(),
                e.getStatusCode()
        );
        return new ResponseEntity<>(errorDTO, HttpStatus.valueOf(e.getStatusCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handle(Exception e) {
        ErrorDTO errorDTO = new ErrorDTO(
                new Timestamp(System.currentTimeMillis()),
                e.getMessage(),
                500
        );
        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
