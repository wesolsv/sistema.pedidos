package com.weszdev.sistema.pedidos.service.venda;

import com.weszdev.sistema.pedidos.exception.CampoInvalidoException;
import com.weszdev.sistema.pedidos.model.Venda;
import com.weszdev.sistema.pedidos.model.VendaItem;
import com.weszdev.sistema.pedidos.repository.VendaRepository;
import com.weszdev.sistema.pedidos.service.produto.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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
        Venda vendaSalva = repository.save(novaVenda);
        atualizarEstoque(vendaSalva.getItens());

        return vendaSalva;
    }

    @Override
    public List<Venda> listarVendas() {
        return repository.findAll();
    }

    private void validacaoDeItens(List<VendaItem> itens) {
        log.info("Verificando produtos");

        if(itens.size() <= 0 ) throw new CampoInvalidoException("itens","A lista de itens da venda está vazia!");
        Set<UUID> ids = new HashSet<>();

        boolean temDuplicado = itens.stream()
                .map(VendaItem::getProduto)
                .anyMatch(produto -> !ids.add(produto.getId()));

        if(temDuplicado) throw new CampoInvalidoException("produtoId","A lista de produtos, não pode conter itens duplicados!");
    }

    private List<VendaItem> criaListaItensVenda(List<VendaItem> itens, Venda venda) {
        log.info("Criando lista de itens de venda");

        return itens.stream()
                .map(item ->
                        new VendaItem(produtoService.buscaProdutoComEstoque(item.getProduto().getId(), item.getQuantidade()),
                                item.getQuantidade(), venda))
                .toList();
    }

    private void atualizarEstoque(List<VendaItem> lista){
        log.info("Atualizando estoque de produtos");

        lista.forEach(item -> {
            produtoService.removerDoEstoque(item.getProduto(), item.getQuantidade());
        });
    }
}
