package com.weszdev.sistema.pedidos.model.dto;

import com.weszdev.sistema.pedidos.model.VendaItem;

import java.util.UUID;

public record ItemRemoveEstoqueDTO (UUID produtoId,
                                    Integer quantidade
) {
    public static ItemRemoveEstoqueDTO fromVendaItem(VendaItem item) {
        return new ItemRemoveEstoqueDTO(
                item.getProduto().getId(),
                item.getQuantidade()
        );
    }
}
