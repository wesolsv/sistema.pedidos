package com.weszdev.sistema.pedidos.repository;

import com.weszdev.sistema.pedidos.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VendaRepository extends JpaRepository<Venda, UUID> {
}
