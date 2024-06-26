package br.com.matheuscorreia.crudjavasebo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.matheuscorreia.crudjavasebo.exception.LivroNotFoundException;
import br.com.matheuscorreia.crudjavasebo.model.Livro;
import br.com.matheuscorreia.crudjavasebo.repository.LivroRepository;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @Transactional
    public Livro create(Livro livro) {
        return livroRepository.save(livro);
    }

    public List<Livro> list() {
        Sort sort = Sort.by(Direction.DESC, "isbn")
                .and(Sort.by(Direction.ASC, "titulo"));
        return livroRepository.findAll(sort);
    }

    @Transactional
    public Livro update(Long id, Livro livro) {
        return livroRepository.findById(id).map(existingLivro -> {
            livro.setId(id);
            return livroRepository.save(livro);
        }).orElseThrow(() -> new LivroNotFoundException("Livro %d não existe!".formatted(id)));
    }

    @Transactional
    public List<Livro> delete(Long id) {
        livroRepository.findById(id).ifPresentOrElse(existingLivro -> {
            livroRepository.delete(existingLivro);
        }, () -> {
            throw new LivroNotFoundException("Livro %d não existe! ".formatted(id));
        });
        return null;
    }

    public Optional<Livro> findById(Long id) {
        return livroRepository.findById(id);
    }

    public List<Livro> findAll() {
        return livroRepository.findAll();
    }
    
    public Livro save(Livro livro) {
        return livroRepository.save(livro);
    }

    public void deleteById(Long id) {
        livroRepository.deleteById(id);
    }
}
