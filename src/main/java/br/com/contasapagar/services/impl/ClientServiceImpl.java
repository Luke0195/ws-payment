package br.com.contasapagar.services.impl;

import br.com.contasapagar.dtos.ClientDto;
import br.com.contasapagar.entities.Client;
import br.com.contasapagar.repositories.ClientRepository;
import br.com.contasapagar.services.ClientService;
import br.com.contasapagar.services.exceptions.ResourceAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public ClientDto createClient(ClientDto dto) {
        Optional<Client> clientAlreadyExists = clientRepository.findByCode(dto.getCode());
        if(clientAlreadyExists.isPresent()) throw new ResourceAlreadyExistsException("This code was already taken!");
        clientAlreadyExists = clientRepository.findByEmail(dto.getEmail());
        if(clientAlreadyExists.isPresent()) throw new ResourceAlreadyExistsException("This email was already taken!");


    }

    @Override
    public Page<ClientDto> findAllPaged(Pageable pageable) {
        return null;
    }

    @Override
    public ClientDto findClientById(Long id) {
        return null;
    }

    @Override
    public ClientDto updateClient(Long id, ClientDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
