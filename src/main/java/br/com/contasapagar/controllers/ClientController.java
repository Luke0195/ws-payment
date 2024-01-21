package br.com.contasapagar.controllers;

import br.com.contasapagar.dtos.ClientDto;
import br.com.contasapagar.services.impl.ClientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/v1")
public class ClientController {
    private final ClientServiceImpl clientServiceImpl;
    @Autowired
    public ClientController(ClientServiceImpl clientServiceImpl) {
        this.clientServiceImpl = clientServiceImpl;
    }
    @PostMapping(value="/client")
    public ResponseEntity<ClientDto> insertClient(@Valid @RequestBody ClientDto dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(this.clientServiceImpl.createClient(dto));
    }
}
