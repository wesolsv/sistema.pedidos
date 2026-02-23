package com.weszdev.sistema.pedidos.model;

import com.weszdev.sistema.pedidos.util.PerfilUsuario;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Table(name = "usuarios")
@Entity(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nomeUsuario;

    private String senha;

    private PerfilUsuario perfilUsuario;

    @OneToOne(mappedBy = "usuario")  // ← aponta para o atributo 'usuario' em Cliente
    private Cliente cliente;

    public Usuario(String nomeUsuario, String senha, PerfilUsuario perfilUsuario){
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.perfilUsuario = perfilUsuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.perfilUsuario == PerfilUsuario.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_PADRAO"));
        else return List.of(new SimpleGrantedAuthority("ROLE_PADRAO"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return nomeUsuario;
    }

}
