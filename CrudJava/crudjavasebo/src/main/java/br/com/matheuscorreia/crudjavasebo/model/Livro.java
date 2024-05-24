// src/main/java/com/sebo/model/Livro.java
package br.com.matheuscorreia.crudjavasebo.model;

import org.apache.commons.lang3.builder.EqualsBuilder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private Long isbn;
    @NotBlank
    private String titulo;
    @NotBlank
    private String sinopse;
    private String autor;
    private double preco;
    private boolean emEstoque;
    
    // Construtores

    public Livro(long id, @NotBlank
            String titulo, @NotBlank
                    String sinopse, String autor, boolean emEstoque, int preco) {
        this.id = id;
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.autor = autor;
        this.preco = preco;
        this.emEstoque = emEstoque;
      }

    public Livro(String titulo, String sinopse, String autor, double preco, boolean emEstoque) {
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.autor = autor;
        this.preco = preco;
        this.emEstoque = emEstoque;
      }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean isEmEstoque() {
        return emEstoque;
    }

    public void setEmEstoque(boolean emEstoque) {
        this.emEstoque = emEstoque;
    
    }

    @Override
    public boolean equals(Object obj) {
      return EqualsBuilder.reflectionEquals(obj, this);
    }
}
