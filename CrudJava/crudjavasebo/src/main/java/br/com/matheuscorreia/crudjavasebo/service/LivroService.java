package br.com.matheuscorreia.crudjavasebo.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import br.com.matheuscorreia.crudjavasebo.exception.BadRequestException;
import br.com.matheuscorreia.crudjavasebo.model.Livro;
import br.com.matheuscorreia.crudjavasebo.repository.LivroRepository;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    //Operações CRUD para cadastro, busca, alteração e exclusão de livros
    public List<Livro> create(Livro livro) {
        livroRepository.save(livro);
        return list();
    }

    public List<Livro> list() {
        Sort sort = Sort.by(Direction.DESC, "isbn").and(
            Sort.by(Direction.ASC,"titulo").ascending()
        );
        return livroRepository.findAll(sort);
    }

    public List<Livro> update(Long id, Livro livro) {
        livroRepository.findById(id).ifPresentOrElse((existingLivro) -> {
            livro.setId(id);
            livroRepository.save(livro);
        }, () -> {
            throw new BadRequestException("Livro %d não existe!".formatted(id));
        });
        return list();
    }

    public List<Livro> delete(Long id) {
        livroRepository.findById(id).ifPresentOrElse((existingLivro) -> livroRepository.delete(existingLivro), () -> {
          throw new BadRequestException("Livro %d não existe! ".formatted(id));
        });
        return list();
      }
    }