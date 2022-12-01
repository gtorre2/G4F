package com.itbam.lojavirtual.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ {

	public static volatile SingularAttribute<Product, Long> estoque;
	public static volatile SingularAttribute<Product, String> codigo;
	public static volatile SingularAttribute<Product, Double> valorVenda;
	public static volatile SingularAttribute<Product, Double> valorCompra;
	public static volatile SingularAttribute<Product, String> nome;
	public static volatile SingularAttribute<Product, Long> id;
	public static volatile SingularAttribute<Product, String> urlImagem;
	public static volatile SingularAttribute<Product, String> descricao;

	public static final String ESTOQUE = "estoque";
	public static final String CODIGO = "codigo";
	public static final String VALOR_VENDA = "valorVenda";
	public static final String VALOR_COMPRA = "valorCompra";
	public static final String NOME = "nome";
	public static final String ID = "id";
	public static final String URL_IMAGEM = "urlImagem";
	public static final String DESCRICAO = "descricao";

}

