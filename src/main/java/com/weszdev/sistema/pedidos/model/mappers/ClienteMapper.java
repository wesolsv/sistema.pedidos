package com.weszdev.sistema.pedidos.model.mappers;

import com.weszdev.sistema.pedidos.model.Cliente;
import com.weszdev.sistema.pedidos.model.dto.ClienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(source = "nomeCompleto", target = "nomeCompleto")
    Cliente toEntity(ClienteDTO dto);

    ClienteDTO toDTO(Cliente cliente);
}
