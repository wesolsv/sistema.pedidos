package com.weszdev.sistema.pedidos.model.mappers;

import com.weszdev.sistema.pedidos.model.Produto;
import com.weszdev.sistema.pedidos.model.dto.ProdutoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    Produto toEntity(ProdutoDTO dto);

    ProdutoDTO toDTO(Produto produto);
}
