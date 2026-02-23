package com.weszdev.sistema.pedidos.model;

import com.weszdev.sistema.pedidos.util.CpfCnpj;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cliente", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 100, nullable = false)
    private String nomeCompleto;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String documento;

    @OneToMany(mappedBy = "cliente")
    private List<Venda> vendas;

    @OneToOne
    @JoinColumn(name = "usuario_id")  // ← dono do relacionamento
    private Usuario usuario;

    public Cliente(String nomeCompleto, LocalDate dataNascimento, String email, String telefone, String documento, Usuario usuario) {
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone = telefone;
        this.documento = documento;
        this.usuario = usuario;
    }
}
