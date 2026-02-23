package com.weszdev.sistema.pedidos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "venda_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "venda_id", nullable = false)
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    private Integer quantidade;

    @Column(name = "preco_unitario")
    private Double precoUnitario;

    private Double subtotal;


    public VendaItem(Produto produto, Integer quantidade, Venda venda) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = produto.getPreco();
        this.subtotal = quantidade * this.precoUnitario;
        this.venda = venda;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        VendaItem vendaItem = (VendaItem) o;
        return produto.getId().equals(vendaItem.produto.getId());
    }

    @Override
    public int hashCode() {
        return produto.hashCode();
    }
}
