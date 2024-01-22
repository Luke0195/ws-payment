package br.com.contasapagar.services;

import br.com.contasapagar.dtos.ClientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    ClientDto createClient(ClientDto dto);
    Page<ClientDto> findAllPaged(Pageable pageable, String name);
    ClientDto findClientById(Long id);
    ClientDto updateClient(Long id, ClientDto dto);
    void delete(Long id);
}
