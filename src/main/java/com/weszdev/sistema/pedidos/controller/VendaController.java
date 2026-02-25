package com.weszdev.sistema.pedidos.controller;

import com.weszdev.sistema.pedidos.model.Usuario;
import com.weszdev.sistema.pedidos.model.Venda;
import com.weszdev.sistema.pedidos.model.dto.VendaDTO;
import com.weszdev.sistema.pedidos.model.dto.VendaResponseDTO;
import com.weszdev.sistema.pedidos.model.mappers.VendaMapper;
import com.weszdev.sistema.pedidos.model.mappers.VendaResponseMapper;
import com.weszdev.sistema.pedidos.service.venda.VendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("venda")
@RequiredArgsConstructor
@Slf4j
public class VendaController implements GenericController{

    private final VendaService service;
    private final VendaMapper mapper;
    private final VendaResponseMapper mapperResponse;

    @PostMapping
    public ResponseEntity<Object> incluirVenda(@Valid @RequestBody VendaDTO vendaDTO){

        Usuario principal = (Usuario) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        Venda venda = mapper.toEntity(vendaDTO);
        venda.setCliente(principal.getCliente());
        service.incluirVenda(venda);

        URI uri = gerarHeaderLocation(venda.getId());

        return ResponseEntity.created(uri).build();
    }

    //lista todos, porém quem tem acesso é só ADMIN
    @GetMapping("listar")
    public ResponseEntity<List<VendaResponseDTO>> listarVendas(){
        List<Venda> vendas = service.listarVendas();
        List<VendaResponseDTO> vendasDTO = vendas.stream().map(mapperResponse::toResponseDTO).toList();
        return ResponseEntity.ok(vendasDTO);
    }

    //lista todos de quem está fazendo a requisição
    @GetMapping("byCliente")
    public ResponseEntity<Object> buscarVendasPorCliente(){
        Usuario principal = (Usuario) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        List<Venda> vendas = service.listarVendasPorCliente(principal.getCliente());
        List<VendaResponseDTO> vendasDTO = vendas.stream().map(mapperResponse::toResponseDTO).toList();
        return ResponseEntity.ok(vendasDTO);
    }

     //traz a venda por número somente ADMIN pode buscar qualquer venda
    @GetMapping("buscaVenda/{numeroVenda}")
    public ResponseEntity<Object> buscaVenda(@PathVariable("numeroVenda") Long numeroVenda){
        Usuario principal = (Usuario) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Venda venda = service.buscaVendaCliente(numeroVenda, principal);
        VendaResponseDTO vendaDTO = mapperResponse.toResponseDTO(venda);
        return ResponseEntity.ok(vendaDTO);
    }
}
