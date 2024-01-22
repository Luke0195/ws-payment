package br.com.contasapagar.services.impl;

import br.com.contasapagar.dtos.BillDto;
import br.com.contasapagar.entities.Bill;
import br.com.contasapagar.entities.Client;
import br.com.contasapagar.entities.Payment;
import br.com.contasapagar.entities.enums.BillStatus;
import br.com.contasapagar.entities.enums.BillType;
import br.com.contasapagar.entities.enums.PaymentStatus;
import br.com.contasapagar.mappers.BillMapper;
import br.com.contasapagar.repositories.BillRepository;
import br.com.contasapagar.repositories.ClientRepository;
import br.com.contasapagar.repositories.PaymentRepository;
import br.com.contasapagar.services.BillService;
import br.com.contasapagar.services.exceptions.DatabaseException;
import br.com.contasapagar.services.exceptions.ResourceNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final PaymentRepository paymentRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public BillServiceImpl(BillRepository billRepository, PaymentRepository paymentRepository, ClientRepository clientRepository){
        this.billRepository = billRepository;
        this.paymentRepository = paymentRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BillDto> findAllBillsPaged(Pageable pageable, String description) {
        if(description.equalsIgnoreCase("")){
            return billRepository.findAll(pageable).map(BillMapper::mapBillEntityToDto);
       }
        List<Bill> findAllBills = billRepository.findAllByDescription(description.toLowerCase());
        Page<Bill> bill = new PageImpl<>(findAllBills, pageable, findAllBills.size());
        return bill.map(BillMapper::mapBillEntityToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public BillDto findBillById(String id) {
        Optional<Bill>  billAlreadyExists = billRepository.findById(UUID.fromString(id));
        Bill bill = billAlreadyExists.orElseThrow(() -> new ResourceNotExistsException("Id not found!"));
        return BillMapper.mapBillEntityToDto(bill);
    }

    @Override
    public void delete(String id) {
        try{
            Optional<Bill> bill = billRepository.findById(UUID.fromString(id));
            Bill billAlreadyExists = bill.orElseThrow(() ->new ResourceNotExistsException("Id not found!") );
            billRepository.delete(billAlreadyExists);
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException("Violation Integraty");
        }
    }

    @Override
    @Transactional
    public BillDto create(BillDto dto) {
        Optional<Client> clientAlreadyExists = clientRepository.findById(dto.getClient().getId());
        Client client = clientAlreadyExists.orElseThrow(() -> new ResourceNotExistsException("client id not found!"));
        Optional<Payment> paymentAlreadyExists = paymentRepository.findById(dto.getPayment().getId());
        Payment payment = paymentAlreadyExists.orElseThrow(() -> new ResourceNotExistsException("payment id not found!"));
        Bill bill = BillMapper.mapBillDtoToEntity(dto, client, payment);
        bill = billRepository.save(bill);
        return BillMapper.mapBillEntityToDto(bill);
    }

    @Override
    @Transactional
    public BillDto update(String id, BillDto dto) {
        Optional<Bill> findBillById = billRepository.findById(UUID.fromString(id));
        Bill billAlreadyExists = findBillById.orElseThrow(() -> new ResourceNotExistsException("Id not found"));
        parseDtoToEntity(dto, billAlreadyExists);
        billAlreadyExists = billRepository.save(billAlreadyExists);
        return BillMapper.mapBillEntityToDto(billAlreadyExists);
    }

    private static void parseDtoToEntity(BillDto dto, Bill bill){
        bill.setDescription(dto.getDescription());
        bill.setValidateDate(dto.getValidateDate());
        bill.setPaymentDate(dto.getPaymentDate());
        bill.setPrice(dto.getPrice());
        bill.setAmountPaid(dto.getAmountPaid());
        bill.setBillType(BillType.valueOf(String.valueOf(dto.getBillType())));
        bill.setBillStatus(BillStatus.valueOf(String.valueOf(dto.getBillStatus())));
        bill.setPaymentStatus(PaymentStatus.valueOf(String.valueOf(dto.getPaymentStatus())));
        bill.setClient(dto.getClient());
        bill.setPayment(dto.getPayment());
    }
}
