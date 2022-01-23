package com.webpage.predictpoliticalpartyprice.exceptionhandling;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception handler for all controllers
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Handles all exceptions thrown
     * @param exception Exception thrown
     * @param request request in which exception was thrown
     * @return Error Response
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest request){
        logger.info(exception.getClass().getName()+ " occured with message "+exception.getMessage());

        return new ResponseEntity<>("Internal server Error: "+exception.getClass().getName()+" occured", new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);

    }


}
