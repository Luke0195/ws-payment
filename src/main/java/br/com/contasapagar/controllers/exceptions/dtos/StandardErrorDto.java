package br.com.contasapagar.controllers.exceptions.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StandardErrorDto implements Serializable {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;
    private String message;
    @JsonProperty("field_errors")
    private List<FieldErrorValidation> fieldErrors = new ArrayList<>();

}
