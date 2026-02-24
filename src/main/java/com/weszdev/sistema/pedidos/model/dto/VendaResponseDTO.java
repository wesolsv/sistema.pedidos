package com.weszdev.sistema.pedidos.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public record VendaResponseDTO(
        LocalDateTime data,
        Double valorTotal,
        List<ItemVendaResponseDTO> itens,
        ClienteResumoDTO cliente
) {
}
