package br.com.contasapagar.services;

import br.com.contasapagar.dtos.BillDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BillService {

    Page<BillDto> findAllBillsPaged(Pageable pageable, String description);
    BillDto findBillById(String id);
    void  delete(String id);
    BillDto create(BillDto dto);

    BillDto update(String id, BillDto dto);

}
