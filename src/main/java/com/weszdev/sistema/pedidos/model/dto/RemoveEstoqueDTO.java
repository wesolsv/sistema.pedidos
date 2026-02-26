package com.weszdev.sistema.pedidos.model.dto;


import java.util.List;
import java.util.UUID;

public record RemoveEstoqueDTO (UUID idVenda, List<ItemRemoveEstoqueDTO> itens){
}
