package br.com.contasapagar.services.impl;

import br.com.contasapagar.dtos.ClientDto;
import br.com.contasapagar.entities.Client;
import br.com.contasapagar.mappers.ClientMapper;
import br.com.contasapagar.repositories.ClientRepository;
import br.com.contasapagar.services.ClientService;
import br.com.contasapagar.services.exceptions.ResourceAlreadyExistsException;
import br.com.contasapagar.services.exceptions.ResourceNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public ClientDto createClient(ClientDto dto) {
        Optional<Client> clientAlreadyExists = clientRepository.findByCode(dto.getCode());
        if (clientAlreadyExists.isPresent()) throw new ResourceAlreadyExistsException("This code was already taken!");
        clientAlreadyExists = clientRepository.findByEmail(dto.getEmail());
        if (clientAlreadyExists.isPresent()) throw new ResourceAlreadyExistsException("This email was already taken!");
        Client client = ClientMapper.mapDtoToEntity(dto);
        client = clientRepository.save(client);
        return ClientMapper.mapEntityToDto(client);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClientDto> findAllPaged(Pageable pageable, String name) {
        if (name.equalsIgnoreCase("")) {
            return this.clientRepository.findAll(pageable).map(ClientMapper::mapEntityToDto);
        } else {
            List<Client> sortedClients = clientRepository.searchClientsByName(name.toLowerCase());
            PageImpl<Client> pageImpl = new PageImpl<>(sortedClients, pageable, sortedClients.size());
            return pageImpl.map(ClientMapper::mapEntityToDto);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDto findClientById(String id) {
        Optional<Client> findClientById = clientRepository.findById(UUID.fromString(id));
        Client clientExists = findClientById.orElseThrow(() -> new ResourceNotExistsException("Id not found!"));
        return ClientMapper.mapEntityToDto(clientExists);
    }

    @Override
    public ClientDto updateClient(Long id, ClientDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
