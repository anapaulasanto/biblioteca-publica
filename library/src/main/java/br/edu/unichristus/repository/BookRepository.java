package br.edu.unichristus.repository;

import br.edu.unichristus.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByCategoryId(Long categoryId);
    Optional<Book> findByIsbn(String isbn);
}
