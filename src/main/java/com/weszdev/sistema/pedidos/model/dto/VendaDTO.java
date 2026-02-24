package com.weszdev.sistema.pedidos.model.dto;


import java.time.LocalDateTime;
import java.util.List;

public record VendaDTO(
        List<ItemVendaDTO> itens
) {}
