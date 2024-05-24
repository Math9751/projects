package br.com.matheuscorreia.crudjavasebo.repository;

import br.com.matheuscorreia.crudjavasebo.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}