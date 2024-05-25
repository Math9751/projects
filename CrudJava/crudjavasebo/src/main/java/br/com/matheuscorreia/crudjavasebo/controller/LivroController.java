package br.com.matheuscorreia.crudjavasebo.controller;

// import java.util.List;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
// import br.com.matheuscorreia.crudjavasebo.exception.LivroNotFoundException;

@RestController
@RequestMapping("/api/livros")
@Validated
public class LivroController {

    private static final Logger log = LoggerFactory.getLogger(LivroController.class);
    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
public ResponseEntity<Object> createLivro(@Valid @RequestBody Livro livro) {
    log.debug("Request to create Livro: {}", livro);
    Livro createdLivro = livroService.create(livro);
    if (createdLivro != null) {
        log.info("Livro criado com sucesso: {}", createdLivro);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLivro);
    } else {
        log.warn("Falha ao criar livro");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}

@GetMapping("/{id}")
public ResponseEntity<Livro> getLivroById(@PathVariable Long id) {
    Optional<Livro> livroOptional = livroService.findById(id);
    if (livroOptional.isPresent()) {
        return ResponseEntity.ok(livroOptional.get());
    } else {
        return ResponseEntity.notFound().build();
    }
}


    @PutMapping("/{id}")
    public ResponseEntity<Livro> updateLivro(@PathVariable Long id, @RequestBody Livro livro) {
        try {
            return livroService.findById(id)
                    .map(existingLivro -> {
                        existingLivro.setIsbn(livro.getIsbn());
                        existingLivro.setTitulo(livro.getTitulo());
                        existingLivro.setSinopse(livro.getSinopse());
                        existingLivro.setAutor(livro.getAutor());
                        existingLivro.setPreco(livro.getPreco());
                        existingLivro.setEmEstoque(livro.isEmEstoque());
                        livroService.save(existingLivro);
                        return ResponseEntity.ok(existingLivro);
                    })
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception ex) {
            log.error("Erro ao atualizar livro: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    


    // @DeleteMapping("/{id}")
    // public List<Livro> delete(@PathVariable("id") Long id) {
    //     return livroService.delete(id);
    // }

}