package br.com.contasapagar.mappers;

import br.com.contasapagar.dtos.BillDto;
import br.com.contasapagar.entities.Bill;
import br.com.contasapagar.entities.Client;
import br.com.contasapagar.entities.Payment;
import br.com.contasapagar.entities.enums.BillStatus;
import br.com.contasapagar.entities.enums.BillType;
import br.com.contasapagar.entities.enums.PaymentStatus;
import jakarta.persistence.Entity;

public final class BillMapper {
    private BillMapper(){}
    public static Bill mapBillDtoToEntity(BillDto dto, Client client, Payment payment){
         return Bill
                 .builder()
                 .description(dto.getDescription())
                 .validateDate(dto.getValidateDate())
                 .paymentDate(dto.getPaymentDate())
                 .price(dto.getPrice())
                 .amountPaid(dto.getAmountPaid())
                 .billType(dto.getBillType())
                 .billStatus(dto.getBillStatus())
                 .paymentStatus(dto.getPaymentStatus())
                 .client(client)
                 .payment(payment)
                 .build();
    }

    public static BillDto mapBillEntityToDto(Bill entity){
        return BillDto
                .builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .validateDate(entity.getValidateDate())
                .paymentDate(entity.getPaymentDate())
                .price(entity.getPrice())
                .amountPaid(entity.getAmountPaid())
                .billType(entity.getBillType())
                .billStatus(entity.getBillStatus())
                .paymentStatus(entity.getPaymentStatus())
                .client(entity.getClient())
                .payment(entity.getPayment())
                .build();
    }
}
