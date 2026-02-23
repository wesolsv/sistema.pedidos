package com.weszdev.sistema.pedidos.service.produto;

import com.weszdev.sistema.pedidos.model.Produto;
import com.weszdev.sistema.pedidos.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;

    @Override
    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    @Override
    public Optional<Produto> buscarPorId(UUID produtoId) {
        System.out.println("Chamou o banco");
        return repository.findById(produtoId);
    }

    @Override
    public Produto atualizar(Produto produto) {
        return repository.save(produto);
    }

    @Override
    public void deletar(Produto produto) {
        repository.delete(produto);
    }

    @Override
    @Cacheable("produtos")
    public List<Produto> listarProdutos() {
       return repository.findAll();
    }
}
