package com.weszdev.sistema.pedidos.consumer;

import com.weszdev.sistema.pedidos.model.VendaItem;
import com.weszdev.sistema.pedidos.model.dto.RemoveEstoqueDTO;
import com.weszdev.sistema.pedidos.model.dto.VendaEventDTO;
import com.weszdev.sistema.pedidos.service.produto.ProdutoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class EstoqueConsumer {

    @Autowired
    private ProdutoService produtoService;

    @RabbitListener(queues = "estoque.queue")
    public void processarEstoque(RemoveEstoqueDTO remove) {
        log.info("Processando baixa de estoque para produtos da venda {}", remove.idVenda());

        remove.itens().forEach(item -> {
            produtoService.removerDoEstoque(item.produtoId(), item.quantidade());
        });

        log.info("Estoque processado com sucesso");
    }
}