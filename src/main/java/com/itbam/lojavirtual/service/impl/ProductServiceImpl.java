package com.itbam.lojavirtual.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itbam.lojavirtual.exception.EntityInvalidException;
import com.itbam.lojavirtual.model.Product;
import com.itbam.lojavirtual.repository.ProductRepository;
import com.itbam.lojavirtual.service.ProductService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ProductServiceImpl extends GenericServiceImpl<Product, Long> implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public Product add(Product product) {
        validateUniqueCodigo(product.getCodigo());
        return repository.save(product);
    }

    @Override
    public Product update(Product product) {
        if (product.getId() == null)
            throw new EntityInvalidException(messagesService.get("id.nulo"));
        Product oldEntity = this.getValidEntity(product.getId());
        if (!oldEntity.getCodigo().equals(product.getCodigo()))
            this.validateUniqueCodigo(product.getCodigo());
        return this.repository.save(product);
    }

    @Override
    public void removeById(Long id) {
        Product product = getValidEntity(id);
        repository.delete(product);
    }

    @Override
    public void removeProducts(Set<Product> entities) {
        if (entities == null)
            throw new EntityNotFoundException(messagesService.get("produtos.nulos"));
        for (Product product : entities) {
            product = getValidEntity(product.getId());
            repository.delete(product);
        }
    }

    @Override
    public List<Product> searchByNomeIgnoreCase(String nome) {
        return repository.findAllByNomeIgnoreCaseContaining(nome);
    }

    @Override
    public List<Product> searchByDescricaoIgnoreCase(String descricao) {
        return repository.findAllByDescricaoIgnoreCaseContaining(descricao);
    }

    @Override
    public List<Product> searchByNomeOrDescricaoIgnoreCase(String nomeOrDescricao) {
        return repository.searchByNomeOrDescricaoIgnoreCase(nomeOrDescricao.toLowerCase());
    }

    @Override
    public Page<Product> searchByNomeOrDescricaoPaginatedIgnoreCase(String nomeOrDescricao, int page, int count,
                                                                    Sort.Direction direction, String sortProperty) {
        return repository.searchByNomeOrDescricaoPaginatedIgnoreCase(
                nomeOrDescricao.toLowerCase(), PageRequest.of(page, count, Sort.by(direction, sortProperty)));
    }

    private Product getValidEntity(Long id) {
        Optional<Product> product = repository.findById(id);
        if (product.isPresent())
            return product.get();
        else
            throw new EntityNotFoundException(messagesService.get("produto.nao.existe"));
    }

    private void validateUniqueCodigo(String codigo) {
        List<Product> productList = repository.findByCodigoIgnoreCase(codigo);
        if (!productList.isEmpty())
            throw new EntityInvalidException(messagesService.get("produto.codigo.igual"));
    }

}
