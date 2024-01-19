package br.com.contasapagar.controllers.exceptions;

import br.com.contasapagar.controllers.exceptions.dtos.StandardErrorDto;
import br.com.contasapagar.services.exceptions.ResourceAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<StandardErrorDto> entityAlreadyExists(HttpServletRequest request, ResourceAlreadyExistsException e){
        StandardErrorDto error = StandardErrorDto
                .builder()
                .timestamp(Instant.now())
                .error("This entity is already exists!")
                .path(request.getRequestURI())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
