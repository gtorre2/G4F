package com.itbam.lojavirtual.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.itbam.lojavirtual.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByNomeIgnoreCaseContaining(String nome);
    List<Product> findAllByDescricaoIgnoreCaseContaining(String descricao);

    List<Product> findByCodigoIgnoreCase(String codigo);

    @Query(value = "SELECT p FROM Product p WHERE LOWER(p.nome) LIKE %?1% OR LOWER(p.descricao) LIKE %?1%")
    List<Product> searchByNomeOrDescricaoIgnoreCase(String nomeOrDescricao);

    @Query(value = "SELECT p FROM Product p WHERE LOWER(p.nome) LIKE %?1% OR LOWER(p.descricao) LIKE %?1%")
    Page<Product> searchByNomeOrDescricaoPaginatedIgnoreCase(String nomeOrDescricao, Pageable page);
}
