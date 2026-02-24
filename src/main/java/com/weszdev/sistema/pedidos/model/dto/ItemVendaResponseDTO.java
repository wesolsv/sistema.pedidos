package com.weszdev.sistema.pedidos.model.dto;

import com.weszdev.sistema.pedidos.model.VendaItem;

public record ItemVendaResponseDTO(
        String nome,
        Integer quantidade,
        Double valor,
        Double subtotal
) {
    public static ItemVendaResponseDTO fromEntity(VendaItem item) {
        return new ItemVendaResponseDTO(
                item.getProduto().getNome(),
                item.getQuantidade(),
                item.getPrecoUnitario(),
                item.getSubtotal()
        );
    }
}
