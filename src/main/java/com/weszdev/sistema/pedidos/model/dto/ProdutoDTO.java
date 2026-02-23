package com.weszdev.sistema.pedidos.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ProdutoDTO(
        UUID id,
        @NotBlank(message = "campo obrigatório")
        String nome,
        @NotBlank(message = "campo obrigatório")
        String modelo,
        @NotNull(message = "campo obrigatório")
        Double preco,
        @NotBlank(message = "campo obrigatório")
        String marca,
        @NotNull(message = "campo obrigatório")
        Integer estoque){}
