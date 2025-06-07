package com.example.ApiSpringThiago.controller;

import com.example.ApiSpringThiago.model.Produto;
import com.example.ApiSpringThiago.service.ProdutoService;
import com.example.ApiSpringThiago.dto.ProdutoDTO;
import com.example.ApiSpringThiago.dto.ProdutoCreateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produto", description = "API de Produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService service;

    @GetMapping
    @Operation(summary = "Listar todos os produtos")
    public List<ProdutoDTO> listarTodos() {
        return service.listarTodos().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID")
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(produto -> ResponseEntity.ok(toDTO(produto)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Criar novo produto")
    public ProdutoDTO criar(@RequestBody ProdutoCreateDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        Produto salvo = service.salvar(produto);
        return toDTO(salvo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar produto")
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoCreateDTO dto) {
        return service.buscarPorId(id)
                .map(p -> {
                    p.setNome(dto.getNome());
                    p.setPreco(dto.getPreco());
                    Produto atualizado = service.salvar(p);
                    return ResponseEntity.ok(toDTO(atualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar produto")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.buscarPorId(id).isPresent()) {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Conversão de Produto para ProdutoDTO
    private ProdutoDTO toDTO(Produto produto) {
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco());
    }
}
