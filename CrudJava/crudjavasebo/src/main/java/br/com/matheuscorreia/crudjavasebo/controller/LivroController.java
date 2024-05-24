package br.com.matheuscorreia.crudjavasebo.controller;

// import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.com.matheuscorreia.crudjavasebo.model.Livro;
import br.com.matheuscorreia.crudjavasebo.service.LivroService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    private static final Logger log = LoggerFactory.getLogger(LivroController.class);
    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping  // Remova a barra do path
public ResponseEntity<Livro> createLivro(@Valid @RequestBody Livro livro) {
    Livro createdLivro = (Livro) livroService.create(livro);
    if (createdLivro != null) {
        log.info("Livro criado com sucesso: {}", createdLivro);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLivro);
    } else {
        log.warn("Falha ao criar livro");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}

    // @GetMapping
    // List<Livro> list() {
    //     return livroService.list();
    // }

    @PutMapping("/{id}") // Adicione a variável de caminho (id) e use o método PUT
    public ResponseEntity<Livro> updateLivro(@PathVariable Long id, @RequestBody Livro livro) {
        Livro updatedLivro = livroService.update(id, livro);
        if (updatedLivro != null) {
            log.info("Livro atualizado com sucesso: {}", updatedLivro);
            return ResponseEntity.ok(updatedLivro);
        } else {
            log.warn("Livro não encontrado para atualização: id={}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
}

    // @DeleteMapping("/{id}")
    // public List<Livro> delete(@PathVariable("id") Long id) {
    //     return livroService.delete(id);
    // }
}