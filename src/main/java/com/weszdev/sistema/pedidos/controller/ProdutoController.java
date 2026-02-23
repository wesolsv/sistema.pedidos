package com.weszdev.sistema.pedidos.controller;

import com.weszdev.sistema.pedidos.model.Produto;
import com.weszdev.sistema.pedidos.model.dto.ProdutoDTO;
import com.weszdev.sistema.pedidos.model.mappers.ProdutoMapper;
import com.weszdev.sistema.pedidos.service.produto.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("produto")
@RequiredArgsConstructor
@Slf4j
public class ProdutoController implements GenericController {

    private final ProdutoService service;
    private final ProdutoMapper mapper;

    @PostMapping
    public ResponseEntity<Object> salvar(@Valid @RequestBody ProdutoDTO produtoDTO){
        log.info("Incluindo novo produto");
        Produto produto = mapper.toEntity(produtoDTO);
        service.salvar(produto);
        URI uri = gerarHeaderLocation(produto.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> alterar(@PathVariable("id") String id, @Valid @RequestBody ProdutoDTO produtoDTO){
        log.info("Atualizando registro de cliente {}", produtoDTO.nome());

        UUID idProduto = UUID.fromString(id);
        Optional<Produto> produtoOptional = service.buscarPorId(idProduto);

        if(produtoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var produto = produtoOptional.get();
        produto.setNome(produtoDTO.nome());
        produto.setModelo(produtoDTO.modelo());
        produto.setMarca(produtoDTO.marca());
        produto.setPreco(produtoDTO.preco());
        service.atualizar(produto);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoDTO> buscaCliente(@PathVariable("id") String id){
        log.info("Buscando informações de produto específico");
        UUID idProduto = UUID.fromString(id);

        return service.buscarPorId(idProduto)
                .map(produto -> {
                    ProdutoDTO dto = mapper.toDTO(produto);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listaProdutos(){
        log.info("Listando produtos");
        List<Produto> listaProdutos = service.listarProdutos();
        List<ProdutoDTO> lista = listaProdutos.stream().map(mapper::toDTO).toList();
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ProdutoDTO> delete(@PathVariable("id") String id){
        log.info("Deletando produto");
        UUID idProduto = UUID.fromString(id);
        Optional<Produto> autorOptional = service.buscarPorId(idProduto);
        if (autorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deletar(autorOptional.get());
        return ResponseEntity.noContent().build();
    }
}
