package com.itbam.lojavirtual.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Set;

public interface GenericService<T, ID> {
    void saveOrUpdate(T product);
    List<T> getAll();
    Page<T> getAllPaginated(int page, int count, Sort.Direction direction, String sortProperty);
    T get(ID id);
    T add(T product);
    T update(T product);
    T addOrUpdate(T product);
    void updateProducts(Set<T> products);
    void remove(T product);
    void removeById(ID id);
    void removeProducts(Set<T> products);
    void removeProductsById(List<ID> productIds);
}
