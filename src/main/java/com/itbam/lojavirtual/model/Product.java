package com.itbam.lojavirtual.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCT")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "codigo")
    private String codigo;

    @NotNull
    @Min(value = 0, message = "Estoque não pode ser menor que 0")
    @Column(name = "estoque")
    private Long estoque;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "urlImagem")
    private String urlImagem;

    @NotNull
    @Min(value = 0, message = "Valor de compra não pode ser menor que zero")
    @Column(name = "valorCompra")
    private Double valorCompra;

    @NotNull
    @Min(value = 0, message = "Valor de venda não pode ser menor que zero")
    @Column(name = "valorVenda")
    private Double valorVenda;

}
