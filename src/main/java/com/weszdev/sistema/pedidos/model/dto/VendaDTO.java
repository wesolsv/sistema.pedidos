package com.weszdev.sistema.pedidos.model.dto;

import java.util.List;
import java.util.UUID;

public record VendaDTO(
        UUID clienteId,
        List<ItemVendaDTO> itens
) {}
