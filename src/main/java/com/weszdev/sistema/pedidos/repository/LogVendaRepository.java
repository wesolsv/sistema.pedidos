package com.weszdev.sistema.pedidos.repository;

import com.weszdev.sistema.pedidos.model.LogVenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LogVendaRepository extends JpaRepository<LogVenda, UUID> {
}
