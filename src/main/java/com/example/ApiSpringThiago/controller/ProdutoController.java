package com.example.ApiSpringThiago.controller;

import com.example.ApiSpringThiago.model.Produto;
import com.example.ApiSpringThiago.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produto", description = "API de Produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService service;

    @GetMapping
    @Operation(summary = "Listar todos os produtos")
    public List<Produto> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Criar novo produto")
    public Produto criar(@RequestBody Produto produto) {
        // Garante que o id será ignorado ao criar
        produto.setId(null);
        return service.salvar(produto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar produto")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        return service.buscarPorId(id)
                .map(p -> {
                    produto.setId(id);
                    return ResponseEntity.ok(service.salvar(produto));
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
}
