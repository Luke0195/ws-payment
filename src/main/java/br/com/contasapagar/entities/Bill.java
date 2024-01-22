package br.com.contasapagar.entities;

import br.com.contasapagar.entities.Client;
import br.com.contasapagar.entities.Payment;
import br.com.contasapagar.entities.enums.BillStatus;
import br.com.contasapagar.entities.enums.BillType;
import br.com.contasapagar.entities.enums.PaymentStatus;
import jakarta.persistence.*;
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
@Entity
@Table(name="tb_bill")
public class Bill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(name="validate_date")
    private LocalDate  validateDate;
    @Column(name="payment_date")
    private LocalDate paymentDate;
    private BigDecimal price;
    @Column(name="amount_paid")
    private BigDecimal amountPaid;
    @Enumerated(EnumType.ORDINAL)
    @Column(name="bill_type")
    private BillType billType;
    @Enumerated(EnumType.ORDINAL)
    @Column(name="bill_status")
    private BillStatus billStatus;
    @Enumerated(EnumType.ORDINAL)
    @Column(name="payment_status")
    private PaymentStatus paymentStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="payment_id")
    private Payment payment;

}
