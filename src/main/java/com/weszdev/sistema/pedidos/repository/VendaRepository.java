package com.weszdev.sistema.pedidos.repository;

import com.weszdev.sistema.pedidos.model.Cliente;
import com.weszdev.sistema.pedidos.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VendaRepository extends JpaRepository<Venda, UUID> {

    List<Venda> findAllByCliente(Cliente idCliente);

    Optional<Venda> findByNumeroPedidoAndClienteId(Long numeroPedido, UUID clienteId);

    Optional<Venda> findByNumeroPedido(Long numeroPedido);

}
