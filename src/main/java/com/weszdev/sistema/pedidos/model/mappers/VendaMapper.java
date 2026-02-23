package com.weszdev.sistema.pedidos.model.mappers;

import com.weszdev.sistema.pedidos.model.Venda;
import com.weszdev.sistema.pedidos.model.dto.VendaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = VendaItemMapper.class)
public interface VendaMapper {

        @Mapping(target = "id", ignore = true)
        @Mapping(target = "data", expression = "java(java.time.LocalDateTime.now())")
        @Mapping(target = "valorTotal", ignore = true)  // Calcula depois
        @Mapping(target = "cliente", ignore = true)     // Busca depois
        @Mapping(target = "itens", source = "itens")    // Delega para VendaItemMapper
        Venda toEntity(VendaDTO dto);

        VendaDTO toDto(Venda entity);
}

