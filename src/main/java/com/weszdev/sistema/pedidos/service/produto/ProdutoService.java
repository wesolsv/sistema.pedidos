package com.weszdev.sistema.pedidos.service.produto;

import com.weszdev.sistema.pedidos.model.Produto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoService {

    Produto salvar(Produto produto);

    Optional<Produto> buscarPorId(UUID produtoId);

    Produto atualizar(Produto produto);

    void deletar(Produto produto);

    List<Produto> listarProdutos();
}
