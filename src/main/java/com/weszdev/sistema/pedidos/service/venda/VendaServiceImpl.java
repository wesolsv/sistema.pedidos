package com.weszdev.sistema.pedidos.service.venda;

import com.weszdev.sistema.pedidos.exception.CampoInvalidoException;
import com.weszdev.sistema.pedidos.exception.InformacaoNaoEncontradaException;
import com.weszdev.sistema.pedidos.model.Cliente;
import com.weszdev.sistema.pedidos.model.Usuario;
import com.weszdev.sistema.pedidos.model.Venda;
import com.weszdev.sistema.pedidos.model.VendaItem;
import com.weszdev.sistema.pedidos.repository.VendaRepository;
import com.weszdev.sistema.pedidos.service.produto.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class VendaServiceImpl implements VendaService{

    private final VendaRepository repository;
    private final ProdutoService produtoService;
    private final VendaPublisher publisher;

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

        TransactionSynchronizationManager.registerSynchronization(
                new TransactionSynchronization() {
                    @Override
                    public void afterCommit() {
                        publisher.publicarVendaCriada(venda);
                    }
                }
        );

        return vendaSalva;
    }

    @Override
    public List<Venda> listarVendas() {
        return repository.findAll();
    }

    @Override
    public List<Venda> listarVendasPorCliente(Cliente cliente) {
        return repository.findAllByCliente(cliente);
    }

    @Override
    public Venda buscaVendaCliente(Long numeroVenda, Usuario principal) {

        Optional<Venda> venda;

        if(principal.getPerfilUsuario().getPerfil().equalsIgnoreCase("PADRAO")){
             venda = repository.findByNumeroPedidoAndClienteId(numeroVenda, principal.getCliente().getId());
             return venda.orElseThrow(() -> new InformacaoNaoEncontradaException("Venda não encontrada para o cliente"));
        }

        venda = repository.findByNumeroPedido(numeroVenda);
        return venda.orElseThrow(() -> new InformacaoNaoEncontradaException("Venda número " + numeroVenda + "não encontrada!"));
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

}
