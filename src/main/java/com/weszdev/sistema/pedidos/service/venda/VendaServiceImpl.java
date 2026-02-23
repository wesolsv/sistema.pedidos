package com.weszdev.sistema.pedidos.service.venda;

import com.weszdev.sistema.pedidos.model.Venda;
import com.weszdev.sistema.pedidos.model.VendaItem;
import com.weszdev.sistema.pedidos.repository.VendaRepository;
import com.weszdev.sistema.pedidos.service.produto.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VendaServiceImpl implements VendaService{

    private final VendaRepository repository;
    private final ProdutoService produtoService;

    @Override
    @Transactional
    public Venda incluirVenda(Venda venda) {

        Venda novaVenda = new Venda();
        novaVenda.setData(venda.getData());
        novaVenda.setCliente(venda.getCliente());

        validacaoDeItens(venda.getItens());

        novaVenda.setItens(criaListaItensVenda(venda.getItens(), novaVenda));
        novaVenda.calcularValorTotal();


        return repository.save(novaVenda);
    }

    private void validacaoDeItens(List<VendaItem> itens) {
        if(itens.size() <= 0 ) throw new RuntimeException("A lista de itens da venda está vazia!");
//        itens.stream()
//                .distinct(item -> it)
    }

    private List<VendaItem> criaListaItensVenda(List<VendaItem> itens, Venda venda) {

        return itens.stream()
                .map(item ->
                        new VendaItem(produtoService.buscaProdutoComEstoque(item.getId(), item.getQuantidade()),
                                item.getQuantidade(), venda))
                .toList();
    }
}
