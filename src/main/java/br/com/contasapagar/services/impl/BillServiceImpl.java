package br.com.contasapagar.services.impl;

import br.com.contasapagar.dtos.BillDto;
import br.com.contasapagar.repositories.BillRepository;
import br.com.contasapagar.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;

    @Autowired
    public BillServiceImpl(BillRepository repository){
        this.billRepository = repository;
    }

    @Override
    @Transactional
    public Page<BillDto> findAllBillsPaged(Pageable pageable) {
        return null;
    }

    @Override
    public BillDto findBillById(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public BillDto create(BillDto dto) {
        return null;
    }
}
