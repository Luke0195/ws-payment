package br.com.contasapagar.services.impl;

import br.com.contasapagar.dtos.ClientDto;
import br.com.contasapagar.entities.Client;
import br.com.contasapagar.mappers.ClientMapper;
import br.com.contasapagar.repositories.ClientRepository;
import br.com.contasapagar.services.ClientService;
import br.com.contasapagar.services.exceptions.ResourceAlreadyExistsException;
import br.com.contasapagar.services.exceptions.ResourceNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        entityIsPresent(clientAlreadyExists, "code");
        clientAlreadyExists = clientRepository.findByEmail(dto.getEmail());
        entityIsPresent(clientAlreadyExists, "email");
        Client client = ClientMapper.mapDtoToEntity(dto);
        client = clientRepository.save(client);
        return ClientMapper.mapEntityToDto(client);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClientDto> findAllPaged(Pageable pageable, String name) {
        if (name.equalsIgnoreCase("")) {
            return parseListToPageDto(this.clientRepository.findAll(pageable));
        } else {
            List<Client> sortedClients = clientRepository.searchClientsByName(name.toLowerCase());
            PageImpl<Client> pageImpl = new PageImpl<>(sortedClients, pageable, sortedClients.size());
            return parseListToPageDto(pageImpl);
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
    @Transactional
    public ClientDto updateClient(String id, ClientDto dto) {
        Client client = clientRepository.getReferenceById(UUID.fromString(id));
        if (Objects.isNull(client.getId())) throw new ResourceNotExistsException("Id not found!");
        copyDtoToEntity(client, dto);
        client = clientRepository.save(client);
        return ClientMapper.mapEntityToDto(client);

    }

    @Override
    public void delete(String id) {
        try {
            Optional<Client> findClientById = clientRepository.findById(UUID.fromString(id));
            if (findClientById.isEmpty()) throw new ResourceNotExistsException("Id not found!");
            this.clientRepository.deleteById(findClientById.get().getId());
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotExistsException("Id not found");
        }
    }

    private static Page<ClientDto> parseListToPageDto(Page<Client> clients) {
        return clients.map(ClientMapper::mapEntityToDto);
    }

    private static void entityIsPresent(Optional<Client> client, String fieldName) {
        if (client.isPresent()) throw new ResourceAlreadyExistsException("This " + fieldName + " was already taken!");
    }

    private static void copyDtoToEntity(Client client, ClientDto dto) {
        client.setName(dto.getName());
        client.setCode(dto.getCode());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());
        client.setAddress(dto.getAddress());
        client.setSublocality(dto.getSublocality());
        client.setCity(dto.getCity());
    }
}
