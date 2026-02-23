package com.weszdev.sistema.pedidos.service.produto;

import com.weszdev.sistema.pedidos.exception.SemEstoqueException;
import com.weszdev.sistema.pedidos.model.Produto;
import com.weszdev.sistema.pedidos.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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

    @Override
    public void incluirEstoque(Produto produto, Integer quantidade) {
        produto.incluirEstoque(quantidade);
        repository.save(produto);
    }

    @Override
    public void removerDoEstoque(Produto produto, Integer quantidade) {
        produto.removerDoEstoque(quantidade);
        repository.save(produto);
    }

    @Override
    public Produto buscaProdutoComEstoque(UUID id, Integer quantidade) {
        Optional<Produto> produtoOptional = repository.findById(id);

        if(!produtoOptional.isPresent()) throw new NoSuchElementException("Produto não encontrado");

        Produto produto = produtoOptional.get();

        if(produto.getEstoque() < quantidade) throw new SemEstoqueException("O produto selecionado não tem estoque disponível, estoque atual: " + produto.getEstoque());

        return produto;
    }

}
