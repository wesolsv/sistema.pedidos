package com.weszdev.sistema.pedidos.model.mappers;

import com.weszdev.sistema.pedidos.model.VendaItem;
import com.weszdev.sistema.pedidos.model.dto.ItemVendaDTO;
import com.weszdev.sistema.pedidos.model.dto.VendaDTO;
import com.weszdev.sistema.pedidos.repository.VendaRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface VendaItemMapper {

    @Mapping(target = "id")
    @Mapping(target = "venda", ignore = true)
    @Mapping(target = "produto", ignore = true)
    @Mapping(target = "subtotal", ignore = true)
    VendaItem toEntity(ItemVendaDTO dto);

    ItemVendaDTO toDto(VendaItem entity);
}
