package com.weszdev.sistema.pedidos.model.dto;

import java.util.UUID;

public record ItemVendaDTO(
        UUID id,
        Integer quantidade
) {}

