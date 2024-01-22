package br.com.contasapagar.utils.factories;

import br.com.contasapagar.controllers.exceptions.dtos.FieldErrorValidation;
import br.com.contasapagar.controllers.exceptions.dtos.StandardErrorDto;

import java.time.Instant;
import java.util.List;

public final class FactoryHepler {

    private FactoryHepler() {
    }


    public static StandardErrorDto mapDtoWithNotListErrors(Integer status, String error, String path, String message) {
        return StandardErrorDto
                .builder()
                .timestamp(Instant.now())
                .status(status)
                .error(error)
                .path(path)
                .message(message)
                .build();
    }

    public static StandardErrorDto mapDtoWithValidationListError(
            Integer status, String error,
            String path, String message,
            List<FieldErrorValidation> fields) {
        return StandardErrorDto
                .builder()
                .timestamp(Instant.now())
                .status(status)
                .error(error)
                .path(path)
                .message(message)
                .fieldErrors(fields)
                .build();
    }
}
