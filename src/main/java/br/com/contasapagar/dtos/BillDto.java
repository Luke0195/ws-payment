package br.com.contasapagar.dtos;

import br.com.contasapagar.entities.Client;
import br.com.contasapagar.entities.Payment;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillDto implements Serializable {

    private UUID id;
    @NotBlank(message = "The field description must be required")
    private String description;

    @NotBlank(message="The field validate_date must be required")
    @JsonProperty("validate_date")
    private LocalDate  validateDate;

    @JsonProperty("payment_date")
    @NotBlank(message="the field payment_date must be required")
    private LocalDate paymentDate;

    @Size(min = 0, max=100000000, message = "The field price must be required")
    private BigDecimal price;

    @JsonProperty("amount_paid")
    @Size(min = 0, max=100000000, message = "The field price must be required")
    private BigDecimal amountPaid;

    @JsonProperty("bill_type")
    private Long billType;

    @JsonProperty("bill_status")
    private Long billStatus;

    @JsonProperty("payment_status")
    private Long paymentStatus;

    @NotBlank(message="The field client must be required")
    private Client client;

    @NotBlank(message="The field payment must be required")
    private Payment payment;

}
