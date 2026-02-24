package com.weszdev.sistema.pedidos.model.mappers;

import com.weszdev.sistema.pedidos.model.Cliente;
import com.weszdev.sistema.pedidos.model.Venda;
import com.weszdev.sistema.pedidos.model.VendaItem;
import com.weszdev.sistema.pedidos.model.dto.ClienteResumoDTO;
import com.weszdev.sistema.pedidos.model.dto.ItemVendaResponseDTO;
import com.weszdev.sistema.pedidos.model.dto.VendaResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VendaResponseMapper {

    VendaResponseDTO toResponseDTO(Venda venda);

    @Mapping(target = "nome", source = "produto.nome")
    @Mapping(target = "valor", source = "precoUnitario")
    ItemVendaResponseDTO toItemDTO(VendaItem item);

    @Mapping(target = "id", source = "cliente.id")
    @Mapping(target = "nome", source = "cliente.nomeCompleto")
    @Mapping(target = "documento", source = "cliente.documento")
    ClienteResumoDTO toClienteDTO(Cliente cliente);

}
