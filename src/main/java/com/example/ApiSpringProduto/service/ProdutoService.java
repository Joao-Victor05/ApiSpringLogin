package com.example.ApiSpringProduto.service;

import com.example.ApiSpringProduto.model.Produto;
import com.example.ApiSpringProduto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    public Optional<Produto> atualizar(Long id, Produto produto) {
        return repository.findById(id).map(existing -> {
            existing.setNome(produto.getNome());
            existing.setPreco(produto.getPreco());
            return repository.save(existing);
        });
    }

    public boolean deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}