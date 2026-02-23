package com.weszdev.sistema.pedidos.model.dto;

import com.weszdev.sistema.pedidos.util.PerfilUsuario;

import java.time.LocalDate;

public record RegisterDTO(String nomeUsuario,
                          String senha,
                          PerfilUsuario perfilUsuario,
                          String nomeCompleto,
                          LocalDate dataNascimento,
                          String email,
                          String telefone,
                          String documento) {
}
