package com.weszdev.sistema.pedidos.service.cliente;


import com.weszdev.sistema.pedidos.model.Cliente;
import com.weszdev.sistema.pedidos.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Caching
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;

    @Override
    public Cliente salvar(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    @Cacheable
    public Optional<Cliente> buscarPorId(UUID clienteId) {
        return repository.findById(clienteId);
    }

    @Override
    public Cliente atualizar(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public void deletar(Cliente cliente) {
        repository.delete(cliente);
    }
}
