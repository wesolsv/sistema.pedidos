package com.weszdev.sistema.pedidos.service.venda;

import com.weszdev.sistema.pedidos.model.Cliente;
import com.weszdev.sistema.pedidos.model.Usuario;
import com.weszdev.sistema.pedidos.model.Venda;

import java.util.List;
import java.util.UUID;

public interface VendaService {

    Venda incluirVenda(Venda venda);

    List<Venda> listarVendas();

    List<Venda> listarVendasPorCliente(Cliente cliente);

    Venda buscaVendaCliente(Long numeroVenda, Usuario principal);
}
