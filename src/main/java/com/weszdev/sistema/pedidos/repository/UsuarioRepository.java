package com.weszdev.sistema.pedidos.repository;

import com.weszdev.sistema.pedidos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    UserDetails findByNomeUsuario(String nomeUsuario);
}
