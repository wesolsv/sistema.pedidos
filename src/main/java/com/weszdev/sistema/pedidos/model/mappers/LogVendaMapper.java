package com.weszdev.sistema.pedidos.model.mappers;

import com.weszdev.sistema.pedidos.model.LogVenda;
import com.weszdev.sistema.pedidos.model.dto.VendaEventDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LogVendaMapper {

    LogVenda toEntity(VendaEventDTO dto);

    VendaEventDTO toDTO(LogVenda logVenda);
}
