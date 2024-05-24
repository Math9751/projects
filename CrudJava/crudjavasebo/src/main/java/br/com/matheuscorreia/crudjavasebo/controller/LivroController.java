package br.com.matheuscorreia.crudjavasebo.controller;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheuscorreia.crudjavasebo.model.Livro;
import br.com.matheuscorreia.crudjavasebo.service.LivroService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    
    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    ResponseEntity<List<Livro>> create(@Valid @RequestBody Livro livro) {
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(livroService.create(livro));
    }

    @GetMapping
    List<Livro> list() {
        return livroService.list();
    }

    @PutMapping("/{id}")
    List<Livro> update(@PathVariable Long id, @RequestBody Livro livro) {
        return livroService.update(id, livro);
    }

    @DeleteMapping("/{id}")
    List<Livro> delete(@PathVariable("id") Long id) {
        return livroService.delete(id);
    }
}