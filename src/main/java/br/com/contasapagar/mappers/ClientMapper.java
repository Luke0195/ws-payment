package br.com.contasapagar.mappers;

import br.com.contasapagar.dtos.ClientDto;
import br.com.contasapagar.entities.Client;

public final class ClientMapper {
    private ClientMapper() {
    }

    public static Client mapDtoToEntity(ClientDto dto) {
        return Client
                .builder()
                .name(dto.getName())
                .code(dto.getCode())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .sublocality(dto.getSublocality())
                .city(dto.getCity())
                .build();
    }

    public static ClientDto mapEntityToDto(Client entity) {
        return ClientDto
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .sublocality(entity.getSublocality())
                .city(entity.getCity())
                .build();
    }
}
