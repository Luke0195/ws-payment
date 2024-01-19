package br.com.contasapagar.controllers.exceptions;

import br.com.contasapagar.controllers.exceptions.dtos.FieldErrorValidation;
import br.com.contasapagar.controllers.exceptions.dtos.StandardErrorDto;
import br.com.contasapagar.services.exceptions.ResourceAlreadyExistsException;
import br.com.contasapagar.utils.factories.FactoryHepler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<StandardErrorDto> entityAlreadyExists(HttpServletRequest request, ResourceAlreadyExistsException e){
        StandardErrorDto error = FactoryHepler.mapDtoWithNotListErrors(
                getHttpStatus(HttpStatus.BAD_REQUEST),
                "This entity already exists!",
                getRequestUri(request),
                e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardErrorDto> fieldValidationError(HttpServletRequest request, MethodArgumentNotValidException e){
        List<FieldErrorValidation> errors = new ArrayList<>();
        e.getFieldErrors().forEach( x -> {
            String fieldName = x.getField();
            String fieldDescription = x.getDefaultMessage();
            errors.add(FieldErrorValidation.builder().fieldName(fieldName).fieldDescription(fieldDescription).build());
        });
        StandardErrorDto error = FactoryHepler.mapDtoWithValidationListError(
                getHttpStatus(HttpStatus.BAD_REQUEST),
                "This entity already exists!",
                getRequestUri(request),
                "Validation Fails", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    private static String getRequestUri(HttpServletRequest request){
        return request.getRequestURI();
    }

    private static Integer getHttpStatus(HttpStatus status){
        return status.value();
    }
}
