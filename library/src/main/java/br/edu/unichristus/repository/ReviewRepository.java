package br.edu.unichristus.repository;

import br.edu.unichristus.domain.model.Book;
import br.edu.unichristus.domain.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByBookId(Long bookId);
    List<Review> findByUserId(Long userId);
}
