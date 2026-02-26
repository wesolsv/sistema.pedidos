package com.weszdev.sistema.pedidos.service.venda;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weszdev.sistema.pedidos.model.Venda;
import com.weszdev.sistema.pedidos.model.VendaItem;
import com.weszdev.sistema.pedidos.model.dto.ItemRemoveEstoqueDTO;
import com.weszdev.sistema.pedidos.model.dto.ItemVendaResponseDTO;
import com.weszdev.sistema.pedidos.model.dto.RemoveEstoqueDTO;
import com.weszdev.sistema.pedidos.model.dto.VendaEventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class VendaPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void publicarVendaCriada(Venda venda) {
        VendaEventDTO event = new VendaEventDTO(venda.getId(), venda.getCliente().getId(), venda.getData());

        RemoveEstoqueDTO remove = new RemoveEstoqueDTO(venda.getId(), converterItens(venda.getItens()));

        rabbitTemplate.convertAndSend("estoque.queue", remove);
//       rabbitTemplate.convertAndSend("email.queue", event);
//       rabbitTemplate.convertAndSend("log.queue", event);

        log.info("Mensagens publicadas para a venda {}", venda.getId());
    }

    private List<ItemRemoveEstoqueDTO> converterItens(List<VendaItem> lista){
        return lista.stream().map(ItemRemoveEstoqueDTO::fromVendaItem).toList();
    }
}
