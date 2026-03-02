package com.weszdev.sistema.pedidos.consumer;

import com.weszdev.sistema.pedidos.model.dto.VendaEventDTO;
import com.weszdev.sistema.pedidos.service.venda.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LogConsumer {

    @Autowired
    private LogService service;

    @RabbitListener(queues = "log.queue")
    public void processarEstoque(VendaEventDTO evento) {
        log.info("Incluindo log de venda");

        service.incluirLog(evento);

        log.info("Estoque processado com sucesso");
    }
}
