package com.weszdev.sistema.pedidos.model.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record VendaEventDTO(
        UUID vendaId,
        UUID clienteId,
        LocalDateTime data
) {}
