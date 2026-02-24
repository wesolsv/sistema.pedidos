package com.weszdev.sistema.pedidos.model.dto;

import java.util.UUID;

public record ClienteResumoDTO(
        UUID id,
        String nome,
        String documento
) {}
