package com.weszdev.sistema.pedidos.service.venda;

import com.weszdev.sistema.pedidos.model.dto.VendaEventDTO;

public interface LogService {

    void incluirLog(VendaEventDTO evento);
}
