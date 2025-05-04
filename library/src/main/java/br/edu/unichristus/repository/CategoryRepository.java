package br.edu.unichristus.repository;

import br.edu.unichristus.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCategoryCode(int categoryCode);
}
