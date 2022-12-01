package com.itbam.lojavirtual.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.itbam.lojavirtual.model.Product;

public interface ProductService extends GenericService<Product, Long>{

    List<Product> searchByNomeIgnoreCase(String nome);
    List<Product> searchByDescricaoIgnoreCase(String descricao);
    List<Product> searchByNomeOrDescricaoIgnoreCase(String nomeOrDescricao);
    Page<Product> searchByNomeOrDescricaoPaginatedIgnoreCase(String nomeOrDescricao, int page, int count,
                                                             Sort.Direction direction, String sortProperty);
}

