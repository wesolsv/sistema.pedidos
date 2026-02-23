package com.weszdev.sistema.pedidos.controller;

import com.weszdev.sistema.pedidos.configuration.security.TokenService;
import com.weszdev.sistema.pedidos.model.Cliente;
import com.weszdev.sistema.pedidos.model.Usuario;
import com.weszdev.sistema.pedidos.model.dto.AuthenticationDTO;
import com.weszdev.sistema.pedidos.model.dto.LoginResponseDTO;
import com.weszdev.sistema.pedidos.model.dto.RegisterDTO;
import com.weszdev.sistema.pedidos.repository.UsuarioRepository;
import com.weszdev.sistema.pedidos.service.cliente.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO infos){
        var usernamePassword = new UsernamePasswordAuthenticationToken(infos.nomeUsuario(), infos.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO){
        if(this.repository.findByNomeUsuario(registerDTO.nomeUsuario()) != null) return ResponseEntity.badRequest().build();

        String senhaEncriptada = new BCryptPasswordEncoder().encode(registerDTO.senha());
        Usuario novoUsuario = this.repository.save(new Usuario(registerDTO.nomeUsuario(), senhaEncriptada, registerDTO.perfilUsuario()));

        this.clienteService.salvar(new Cliente(registerDTO.nomeCompleto(),
                registerDTO.dataNascimento(),
                registerDTO.email(),
                registerDTO.telefone(),
                registerDTO.documento(),
                novoUsuario));

        return ResponseEntity.ok().build();
    }
}
