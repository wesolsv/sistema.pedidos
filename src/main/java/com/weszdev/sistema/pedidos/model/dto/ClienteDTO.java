package com.weszdev.sistema.pedidos.model.dto;

import com.weszdev.sistema.pedidos.util.CpfCnpj;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record ClienteDTO(
        UUID id,
        @NotBlank(message = "campo obrigatório")
        String nomeCompleto,
        @NotNull(message = "campo obrigatório")
        @Past(message = "não pode ser uma data futura")
        LocalDate dataNascimento,
        @NotBlank(message = "campo obrigatório")
        String email,
        @NotBlank(message = "campo obrigatório")
        String telefone,
        @NotBlank
        @CpfCnpj
        @Size(min= 11, max = 14, message = "Campo fora do tamanho padrao")
        String documento){}
