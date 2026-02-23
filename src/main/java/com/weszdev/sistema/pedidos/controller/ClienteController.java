package com.weszdev.sistema.pedidos.controller;

import com.weszdev.sistema.pedidos.model.Cliente;
import com.weszdev.sistema.pedidos.model.dto.ClienteDTO;
import com.weszdev.sistema.pedidos.model.mappers.ClienteMapper;
import com.weszdev.sistema.pedidos.service.cliente.ClienteService;
import com.weszdev.sistema.pedidos.service.cliente.ClienteServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("cliente")
@RequiredArgsConstructor
@Slf4j
public class ClienteController implements GenericController {

    private final ClienteService service;
    private final ClienteMapper mapper;

//    @PostMapping
//    public ResponseEntity<Object> salvar(@Valid @RequestBody ClienteDTO clienteDTO){
//        log.info("Incluindo novo cliente");
//        Cliente cliente = mapper.toEntity(clienteDTO);
//        service.salvar(cliente);
//        URI uri = gerarHeaderLocation(cliente.getId());
//        return ResponseEntity.created(uri).build();
//    }

    @PutMapping("{id}")
    public ResponseEntity<Object> alterar(@PathVariable("id") String id, @Valid @RequestBody ClienteDTO clienteBody){
        log.info("Atualizando registro de cliente {}", clienteBody.nomeCompleto());

        UUID idCliente = UUID.fromString(id);
        Optional<Cliente> clienteOptional = service.buscarPorId(idCliente);

        if(clienteOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var cliente = clienteOptional.get();
        cliente.setNomeCompleto(clienteBody.nomeCompleto());
        cliente.setDataNascimento(clienteBody.dataNascimento());
        cliente.setDocumento(clienteBody.documento());
        cliente.setEmail(clienteBody.email());
        cliente.setTelefone(clienteBody.telefone());
        service.atualizar(cliente);

        return ResponseEntity.noContent().build();

    }

    @GetMapping("{id}")
    public ResponseEntity<ClienteDTO> buscaCliente(@PathVariable("id") String id){
        log.info("Buscando informações de cliente específico");
        UUID idCliente = UUID.fromString(id);

        return service.buscarPorId(idCliente)
                .map(cliente -> {
                    ClienteDTO dto = mapper.toDTO(cliente);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable("id") String id){
        log.info("Deletando cliente");
        UUID idCliente = UUID.fromString(id);
        Optional<Cliente> autorOptional = service.buscarPorId(idCliente);
        if (autorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deletar(autorOptional.get());
        return ResponseEntity.noContent().build();
    }
}
