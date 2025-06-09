package com.example.ApiSpringProduto.repository;

import com.example.ApiSpringProduto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}