package com.weszdev.sistema.pedidos.service.venda;

import com.weszdev.sistema.pedidos.model.Cliente;
import com.weszdev.sistema.pedidos.model.Usuario;
import com.weszdev.sistema.pedidos.model.Venda;

import java.util.List;

public interface VendaService {

    Venda incluirVenda(Venda venda);

    List<Venda> listarVendas();

    List<Venda> listarVendasPorCliente(Cliente cliente);

    Venda buscaVendaCliente(Long numeroVenda, Usuario principal);
}
