package com.weszdev.sistema.pedidos.service.cliente;

import com.weszdev.sistema.pedidos.model.Cliente;

import java.util.Optional;
import java.util.UUID;

public interface ClienteService {

    Cliente salvar(Cliente cliente);

    Optional<Cliente> buscarPorId(UUID clienteId);

    Cliente atualizar(Cliente cliente);

    void deletar(Cliente cliente);
}
