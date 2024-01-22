package br.com.contasapagar.dtos;

import br.com.contasapagar.entities.Client;
import br.com.contasapagar.entities.Payment;
import br.com.contasapagar.entities.enums.BillStatus;
import br.com.contasapagar.entities.enums.BillType;
import br.com.contasapagar.entities.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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


    @JsonProperty("validate_date")
    private LocalDate  validateDate;

    @JsonProperty("payment_date")
    private LocalDate paymentDate;

    @NotNull(message = "price cannot must be required")
    private BigDecimal price;

    @JsonProperty("amount_paid")
    @NotNull(message = "amount_paid cannot must be required")
    private BigDecimal amountPaid;

    @JsonProperty("bill_type")
    private BillType billType;

    @JsonProperty("bill_status")
    private BillStatus billStatus;

    @JsonProperty("payment_status")
    private PaymentStatus paymentStatus;


    private Client client;

    private Payment payment;

}
