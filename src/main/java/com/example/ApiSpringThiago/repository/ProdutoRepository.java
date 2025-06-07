package com.example.ApiSpringThiago.repository;

import com.example.ApiSpringThiago.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}