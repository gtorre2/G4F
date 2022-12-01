package com.itbam.lojavirtual.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.itbam.lojavirtual.exception.EntityInvalidException;
import com.itbam.lojavirtual.service.GenericService;
import com.itbam.lojavirtual.service.MessagesService;

@Service
public abstract class GenericServiceImpl<T, ID extends Serializable> implements GenericService<T, ID> {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private JpaRepository<T, ID> repository;
	
    @Autowired
    public MessagesService messagesService;

	@Override
	public void saveOrUpdate(T product) {		
		try {
			repository.save(product);
		} catch (ConstraintViolationException e) {
			throw new  EntityInvalidException(messagesService.get("produto.codigo.igual"));
		}
	}

	@Override
	public List<T> getAll() {
		return repository.findAll();
	}

	@Override
	public Page<T> getAllPaginated(int page, int count, Sort.Direction direction, String sortProperty) {
		return repository.findAll(PageRequest.of(page, count, Sort.by(direction, sortProperty)));
	}

	@Override
	public T get(ID id) {
		return repository.findById(id).get();
	}

	@Override
	public T add(T product) {
		return repository.save(product);
	}

	@Override
	public T update(T product) {
		return repository.save(product);
	}

	@Override
	public T addOrUpdate(T product) {
		return repository.save(product);
	}

	@Override
	public void updateProducts(Set<T> products) {
		repository.saveAll(products);
	}

	@Override
    public void remove(T product) {
        repository.delete(product);
    }

    @Override
    public void removeById(ID id) {
        repository.deleteById(id);
    }

	@Override
	public void removeProducts(Set<T> products) {
		repository.deleteAll(products);
	}

	@Override
	public void removeProductsById(List<ID> productIds) {
		for (ID id : productIds) {
			repository.deleteById(id);
		}
	}
}
