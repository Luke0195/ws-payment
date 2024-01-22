package br.com.contasapagar.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto implements Serializable {
    private UUID id;
    @NotBlank(message = "The field name must be required")
    private String name;
    @NotBlank(message = "The filed code must be required")
    private String code;
    @Email(message = "Please provided a valid email")
    @NotBlank(message = "The field email must be required")
    private String email;
    private String phone;
    private String address;
    @JsonProperty("sub_locality")
    private String sublocality;
    private String city;
}
