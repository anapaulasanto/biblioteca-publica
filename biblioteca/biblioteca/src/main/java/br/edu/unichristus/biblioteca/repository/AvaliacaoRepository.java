package br.edu.unichristus.biblioteca.repository;

import br.edu.unichristus.biblioteca.domain.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findAll();
}
