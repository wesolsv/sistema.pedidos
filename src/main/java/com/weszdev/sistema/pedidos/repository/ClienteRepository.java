package com.weszdev.sistema.pedidos.repository;

import com.weszdev.sistema.pedidos.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
}
