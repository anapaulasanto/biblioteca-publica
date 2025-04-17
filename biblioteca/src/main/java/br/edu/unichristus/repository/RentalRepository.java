package br.edu.unichristus.repository;

import br.edu.unichristus.domain.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
