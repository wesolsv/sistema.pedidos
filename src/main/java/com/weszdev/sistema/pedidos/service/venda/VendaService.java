package com.weszdev.sistema.pedidos.service.venda;

import com.weszdev.sistema.pedidos.model.Venda;

import java.util.List;

public interface VendaService {

    Venda incluirVenda(Venda venda);

    List<Venda> listarVendas();
}
