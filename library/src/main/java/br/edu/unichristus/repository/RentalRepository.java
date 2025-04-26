package br.edu.unichristus.repository;

import br.edu.unichristus.domain.model.Rental;
import br.edu.unichristus.domain.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByUserId(Long userId);
    List<Rental> findByBookId(Long bookId);

}
