package com.itbam.lojavirtual.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.itbam.lojavirtual.model.Product;
import com.itbam.lojavirtual.service.ProductService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
	
    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Iterable<Product> getAll() {
        return service.getAll();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<Product> getAllPaginated(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "count", defaultValue = "10", required = false) int count,
            @RequestParam(value = "order", defaultValue = "ASC", required = false) Sort.Direction direction,
            @RequestParam(value = "sort", defaultValue = "nome", required = false) String sortProperty) {

        return service.getAllPaginated(page, count, direction, sortProperty);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product find(
    		@PathVariable Long id) {
        Product product = service.get(id);
        if (product == null) {
            throw new EntityNotFoundException("produto.nao.existe");
        } else {
            return product;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Product create(
    		@RequestBody @Valid Product product) {
        return service.add(product);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Product update(
    		@RequestBody @Valid Product product) {
        return service.update(product);
    }

    @RequestMapping(value = "/nome/{nome}", method = RequestMethod.GET)
    public List<Product> searchByNome(
            @PathVariable(name = "nome") 
            @NotNull(message = "Nao pode ser nulo")  String nome) {
        return service.searchByNomeIgnoreCase(nome);
    }


    @RequestMapping(value = "/descricao/{descricao}", method = RequestMethod.GET)
    public List<Product> searchByDescricao(
            @PathVariable(name = "descricao") 
            @NotNull(message = "Nao pode ser nulo")  String descricao) {
        return service.searchByDescricaoIgnoreCase(descricao);
    }

    @RequestMapping(value = "/nomeOrDescricao/{nomeOrDescricao}", method = RequestMethod.GET)
    public List<Product> searchByNomeOrDescricao(
            @PathVariable(name = "nomeOrDescricao") 
            @NotNull(message = "Nao pode ser nulo") String nomeOrDescricao) {
        return service.searchByNomeOrDescricaoIgnoreCase(nomeOrDescricao);
    }

    @RequestMapping(value = "/nomeOrDescricaoPaginated/{nomeOrDescricao}", method = RequestMethod.GET)
    public Page<Product> searchByNomeOrDescricaoPaginated(
            @PathVariable(name = "nomeOrDescricao") 
            @NotNull(message = "Nao pode ser nulo") String nomeOrDescricao,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "count", defaultValue = "10", required = false) int count,
            @RequestParam(value = "order", defaultValue = "ASC", required = false) Sort.Direction direction,
            @RequestParam(value = "sort", defaultValue = "nome", required = false) String sortProperty) {

        return service.searchByNomeOrDescricaoPaginatedIgnoreCase(nomeOrDescricao, page, count, direction, sortProperty);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public HttpEntity<Product> delete(
    		@PathVariable Long id) {
        service.removeById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public HttpEntity<Product> deleteAll(
    		@RequestBody Set<Product> products) {
        service.removeProducts(products);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
