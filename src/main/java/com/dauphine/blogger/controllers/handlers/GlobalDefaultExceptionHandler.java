package com.dauphine.blogger.controllers.handlers;

import com.dauphine.blogger.exceptions.CategoryAlreadyExistsException;
import com.dauphine.blogger.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger.exceptions.PostNotFoundByIdException;
import org.apache.coyote.Response;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>
 * Global exception handler for the Blogger Box application.
 * </p>
 *
 * <p>
 * This class is annotated with {@link ControllerAdvice}, indicating that it serves as an exception handler for
 * controllers. It handles exceptions of type {@link CategoryAlreadyExistsException} and {@link PostNotFoundByIdException}.
 * </p>
 *
 * @author Nelson PROIA <nelson.proia@dauphine.eu>
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    /**
     * The logger object to log the exception that occur during runtime.
     */
    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    /**
     * Exception handler for CategoryNotFoundByIdException and PostNotFoundByIdException.
     * Logs the warning message and returns an appropriate ResponseEntity.
     *
     * @param e The exception to handle
     * @return ResponseEntity containing the status code and error message
     */
    @ExceptionHandler({CategoryNotFoundByIdException.class, PostNotFoundByIdException.class})
    public ResponseEntity<String> handleNotFoundException(Exception e) {
        logger.warn("[NOT FOUND] {}", e.getMessage());

        return ResponseEntity
                .status(404)
                .body(e.getMessage());
    }

    /**
     * Exception handler for CategoryAlreadyExistsException.
     * Logs the warning message and returns a ResponseEntity with a 400 status code.
     *
     * @param e The exception to handle
     * @return ResponseEntity containing the status code and error message
     */
    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<String> handleBadRequestException(Exception e) {
        logger.warn("[BAD REQUEST] {}", e.getMessage());

        return ResponseEntity
                .status(400)
                .body(e.getMessage());
    }

    /**
     * Exception handler for PSQLException.
     * Logs the warning message and returns a ResponseEntity with a 409 status code.
     *
     * @param e The exception to handle
     * @return ResponseEntity containing the status code and error message
     */
    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<String> handleConflictException(Exception e) {
        logger.warn("[CONFLICT] {}", e.getMessage());

        return ResponseEntity
                .status(409)
                .body(e.getMessage());
    }

}
