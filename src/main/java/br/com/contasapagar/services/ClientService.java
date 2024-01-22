package br.com.contasapagar.services;

import br.com.contasapagar.dtos.ClientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    ClientDto createClient(ClientDto dto);

    Page<ClientDto> findAllPaged(Pageable pageable, String name);

    ClientDto findClientById(String id);

    ClientDto updateClient(String id, ClientDto dto);

    void delete(String id);
}
