package com.weszdev.sistema.pedidos.repository;

import com.weszdev.sistema.pedidos.model.VendaItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VendaItemRepository extends JpaRepository<VendaItem, UUID> {
}
